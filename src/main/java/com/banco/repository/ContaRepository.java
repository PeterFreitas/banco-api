package com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	Boolean existsByCpf(String cpf);

	Boolean existsByEmail(String email);
}