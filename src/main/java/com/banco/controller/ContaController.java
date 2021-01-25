package com.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banco.model.Conta;
import com.banco.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	private ContaService contaService;

	public ContaController(ContaService contaService) {
		super();
		this.contaService = contaService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@RequestBody Conta conta) {
		return contaService.criar(conta);
	}
}