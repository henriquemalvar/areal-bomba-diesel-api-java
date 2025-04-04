package com.example.demo.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "maquinarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Maquinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // 'Draga', 'Caminhão', 'Pá Carregadeira'

    @Column(name = "codigo_bomba", nullable = false)
    private String codigoBomba;

    @Column(name = "data_fabricacao", nullable = false)
    private LocalDate dataFabricacao;

    @OneToMany(mappedBy = "maquinario")
    private List<Reabastecimento> abastecimentos;
}