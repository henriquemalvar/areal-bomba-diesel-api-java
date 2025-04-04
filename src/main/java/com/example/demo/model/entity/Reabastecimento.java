package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reabastecimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reabastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maquinario_id", nullable = false)
    private Maquinario maquinario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Float litros;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "bomba_id")
    private Bomba bomba;
}