import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import {aws_ecs_patterns} from "aws-cdk-lib";
import * as ecr from 'aws-cdk-lib/aws-ecr';
import {ContainerImage} from "aws-cdk-lib/aws-ecs";

export class AssemblyStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const vpc = new ec2.Vpc(this, 'assembly-stack-vpc', {
        maxAzs: 2,
        natGateways: 1
    });

    const applicationCluster = new ecs.Cluster(this, 'assembly-stack-cluster', {
        vpc,
        clusterName: 'assembly-stack-cluster'
    });

    const repository = ecr.Repository.fromRepositoryName(this, 'assembly-stack-repository', 'assembly-app');
    const image = ContainerImage.fromEcrRepository(repository, 'latest');

    const assemblyApp = new aws_ecs_patterns.ApplicationLoadBalancedFargateService(this,
        'assembly-stack-service',
        {
            cluster: applicationCluster,
            desiredCount: 1,
            cpu: 256,
            memoryLimitMiB: 512,
            taskImageOptions: {
                image: image,
                containerPort: 8080
            }
        });

    assemblyApp.targetGroup.configureHealthCheck({
        port: 'traffic-port',
        path: '/actuator/health',
        healthyHttpCodes: '200'
    });

    const autoScaling = assemblyApp.service.autoScaleTaskCount({
        minCapacity: 1,
        maxCapacity: 2,
    });

    autoScaling.scaleOnCpuUtilization('cpu-scaling', {
        targetUtilizationPercent: 50,
        policyName: 'assembly-cpu-autoscaling-policy',
        scaleInCooldown: cdk.Duration.seconds(60),
        scaleOutCooldown: cdk.Duration.seconds(60)
    });
  }
}
