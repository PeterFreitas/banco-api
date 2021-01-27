package com.banco.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.model.Conta;
import com.banco.repository.ContaRepository;
import com.banco.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public List<Conta> buscar() {
		return contaRepository.findAll();
	}

	@Override
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