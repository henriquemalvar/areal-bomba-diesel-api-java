package com.example.demo.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private FuncaoDTO funcao;
    private EnderecoUsuarioDTO endereco;
    private LocalDateTime createdAt;
}