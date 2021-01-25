package com.banco.service;

import java.util.List;

import com.banco.model.Conta;

public interface ContaService {
	List<Conta> buscar();

	Conta criar(Conta conta);
}