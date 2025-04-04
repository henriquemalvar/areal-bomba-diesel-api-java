package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Bomba;
import com.example.demo.repository.BombaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BombaService {

    private final BombaRepository bombaRepository;

    @Transactional
    public Bomba create(Bomba bomba) {
        return bombaRepository.save(bomba);
    }

    public Bomba findById(Long id) {
        return bombaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bomba não encontrada"));
    }

    @Transactional
    public Bomba update(Long id, Bomba bomba) {
        Bomba existingBomba = findById(id);
        existingBomba.setCapacidade(bomba.getCapacidade());
        existingBomba.setLocalizacao(bomba.getLocalizacao());
        return bombaRepository.save(existingBomba);
    }

    @Transactional
    public void delete(Long id) {
        bombaRepository.deleteById(id);
    }
}