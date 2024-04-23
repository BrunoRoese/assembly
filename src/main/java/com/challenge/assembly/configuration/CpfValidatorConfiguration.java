package com.challenge.assembly.configuration;

import com.challenge.assembly.configuration.error.CpfErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CpfValidatorConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CpfErrorDecoder();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
