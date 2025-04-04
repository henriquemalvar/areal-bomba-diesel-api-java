package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NovoUsuarioDTO {
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String telefone;
    private LocalDate dataNascimento;
    private Long funcaoId;
    private EnderecoUsuarioDTO endereco;
}