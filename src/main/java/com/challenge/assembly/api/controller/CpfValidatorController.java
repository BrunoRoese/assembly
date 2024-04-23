package com.challenge.assembly.api.controller;

import com.challenge.assembly.cpfclient.CpfValidatorClient;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cpf-validator")
@RequiredArgsConstructor
public class CpfValidatorController {

    private final CpfValidatorClient cpfValidatorClient;

    @GetMapping("/{cpf}")
    public String validateCpf(@PathVariable("cpf") String cpf) {
        try {
            return cpfValidatorClient.validateCpf(cpf);
        } catch (NotFoundException e) {
            throw new BadRequestException("Invalid CPF");
        }
    }
}
