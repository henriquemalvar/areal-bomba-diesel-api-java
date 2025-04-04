package com.example.demo.service;

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
        reabastecimentoRepository.deleteById(id);
    }
}