package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Reabastecimento;

@Repository
public interface ReabastecimentoRepository extends JpaRepository<Reabastecimento, Long> {
    List<Reabastecimento> findByDataBetweenAndMaquinarioId(LocalDate dataInicio, LocalDate dataFim, Long maquinarioId);

    List<Reabastecimento> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Reabastecimento> findByMaquinarioId(Long maquinarioId);
}