package com.banco.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	Boolean existsByCpf(String cpf);

	Boolean existsByEmail(String email);
}