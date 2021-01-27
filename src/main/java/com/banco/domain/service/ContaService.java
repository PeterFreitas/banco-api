package com.banco.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.domain.exception.NegocioException;
import com.banco.domain.model.Conta;
import com.banco.domain.repository.ContaRepository;

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
			throw new NegocioException("CPF já cadastrado!");
		}
		Boolean existeEmail = contaRepository.existsByEmail(novaConta.getEmail());
		if (existeEmail) {
			throw new NegocioException("Email já cadastrado!");
		}
	}
}