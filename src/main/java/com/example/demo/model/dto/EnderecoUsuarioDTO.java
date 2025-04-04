package com.example.demo.model.dto;

import lombok.Data;

@Data
public class EnderecoUsuarioDTO {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;
}