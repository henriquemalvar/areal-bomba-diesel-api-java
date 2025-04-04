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
@Table(name = "compras_combustivel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompraCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private Float litros;

    @Column(name = "preco_litro", nullable = false)
    private Float precoLitro;

    @Column(name = "preco_total", nullable = false)
    private Float precoTotal;

    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;
}