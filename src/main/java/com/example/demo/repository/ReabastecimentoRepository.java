package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Reabastecimento;

@Repository
public interface ReabastecimentoRepository extends JpaRepository<Reabastecimento, Long> {
    List<Reabastecimento> findByDataBetweenAndMaquinarioId(LocalDateTime dataInicio, LocalDateTime dataFim,
            Long maquinarioId);

    List<Reabastecimento> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<Reabastecimento> findByMaquinarioId(Long maquinarioId);
}