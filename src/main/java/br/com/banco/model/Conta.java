package br.com.banco.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
public class Conta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo Nome não pode ser vazio ou nulo!")
	private String nome;

	@NotEmpty(message = "Campo Email não pode ser vazio ou nulo!")
	@Email(message = "Email precisa ser válido!")
	private String email;

	@NotEmpty(message = "Campo CPF não pode ser vazio ou nulo!")
	@CPF(message = "CPF precisa ser válido")
	private String cpf;

	@NotNull(message = "Campo CPF não pode ser vazio ou nulo!")
	private LocalDate dataNascimento;
}