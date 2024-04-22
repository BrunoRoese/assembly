package com.challenge.assembly.api.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CpfValidatorConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CpfErrorDecoder();
    }
}
