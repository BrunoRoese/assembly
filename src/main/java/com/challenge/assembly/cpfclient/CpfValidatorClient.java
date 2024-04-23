package com.challenge.assembly.cpfclient;

import com.challenge.assembly.configuration.CpfValidatorConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cpf-validator", url = "${cpf.validator.url}", configuration = CpfValidatorConfiguration.class)
public interface CpfValidatorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{cpf}")
    String validateCpf(@PathVariable("cpf") String cpf);
}
