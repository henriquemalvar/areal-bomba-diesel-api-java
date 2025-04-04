package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private Integer numero;
}