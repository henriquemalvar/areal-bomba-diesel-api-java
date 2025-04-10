package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Reabastecimento;
import com.example.demo.repository.ReabastecimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReabastecimentoService {

    private final ReabastecimentoRepository reabastecimentoRepository;

    @Transactional
    public Reabastecimento create(Reabastecimento reabastecimento) {
        return reabastecimentoRepository.save(reabastecimento);
    }

    public List<Reabastecimento> findAll(LocalDate dataInicio, LocalDate dataFim, Long maquinarioId) {
        if (dataInicio != null && dataFim != null && maquinarioId != null) {
            return reabastecimentoRepository.findByDataBetweenAndMaquinarioId(dataInicio, dataFim, maquinarioId);
        } else if (dataInicio != null && dataFim != null) {
            return reabastecimentoRepository.findByDataBetween(dataInicio, dataFim);
        } else if (maquinarioId != null) {
            return reabastecimentoRepository.findByMaquinarioId(maquinarioId);
        }
        return reabastecimentoRepository.findAll();
    }

    public Reabastecimento findById(Long id) {
        return reabastecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reabastecimento não encontrado"));
    }

    @Transactional
    public Reabastecimento update(Long id, Reabastecimento reabastecimento) {
        Reabastecimento existingReabastecimento = findById(id);
        existingReabastecimento.setMaquinario(reabastecimento.getMaquinario());
        existingReabastecimento.setUsuario(reabastecimento.getUsuario());
        existingReabastecimento.setLitros(reabastecimento.getLitros());
        existingReabastecimento.setData(reabastecimento.getData());
        existingReabastecimento.setBomba(reabastecimento.getBomba());
        return reabastecimentoRepository.save(existingReabastecimento);
    }

    @Transactional
    public void delete(Long id) {
        if (!reabastecimentoRepository.existsById(id)) {
            throw new RuntimeException("Reabastecimento não encontrado");
        }
        reabastecimentoRepository.deleteById(id);
    }
}