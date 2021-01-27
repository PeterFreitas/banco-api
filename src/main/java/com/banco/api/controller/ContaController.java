package com.banco.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banco.domain.model.Conta;
import com.banco.domain.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@RequestBody @Valid Conta novaConta) {
		return contaService.criar(novaConta);
	}
}