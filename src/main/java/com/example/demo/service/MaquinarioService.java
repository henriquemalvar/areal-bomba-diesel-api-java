package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Maquinario;
import com.example.demo.repository.MaquinarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaquinarioService {

    private final MaquinarioRepository maquinarioRepository;

    @Transactional
    public Maquinario create(Maquinario maquinario) {
        if (maquinarioRepository.existsByCodigoBomba(maquinario.getCodigoBomba())) {
            throw new RuntimeException("Código da bomba já cadastrado");
        }
        return maquinarioRepository.save(maquinario);
    }

    public Maquinario findById(Long id) {
        return maquinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquinário não encontrado"));
    }

    @Transactional
    public Maquinario update(Long id, Maquinario maquinario) {
        Maquinario existingMaquinario = findById(id);
        existingMaquinario.setNome(maquinario.getNome());
        existingMaquinario.setTipo(maquinario.getTipo());
        existingMaquinario.setCodigoBomba(maquinario.getCodigoBomba());
        existingMaquinario.setDataFabricacao(maquinario.getDataFabricacao());
        return maquinarioRepository.save(existingMaquinario);
    }

    @Transactional
    public void delete(Long id) {
        maquinarioRepository.deleteById(id);
    }
}