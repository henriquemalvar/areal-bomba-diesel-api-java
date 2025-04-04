package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PessoaDTO {
	
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private String telefone;
	
	private String email;
}
