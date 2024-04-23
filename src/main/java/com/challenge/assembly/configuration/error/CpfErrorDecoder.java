package com.challenge.assembly.configuration.error;

import com.challenge.assembly.api.exception.BadRequestException;
import feign.Response;

public class CpfErrorDecoder implements feign.codec.ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() == 404) {
                return new BadRequestException("Invalid CPF");
            }

            return new RuntimeException("Internal Server Error");
        }
}
