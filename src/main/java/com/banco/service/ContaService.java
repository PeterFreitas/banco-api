package com.banco.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.model.Conta;
import com.banco.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	public Conta criar(Conta novaConta) {
		validar(novaConta);
		return contaRepository.save(novaConta);
	}

	private void validar(Conta novaConta) {
		Boolean existeCpf = contaRepository.existsByCpf(novaConta.getCpf());
		if (existeCpf) {
			throw new RuntimeException("CPF já existe!");
		}
		Boolean existeEmail = contaRepository.existsByEmail(novaConta.getEmail());
		if (existeEmail) {
			throw new RuntimeException("Email já existe!");
		}
	}	
}