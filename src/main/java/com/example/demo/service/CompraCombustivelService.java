package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.CompraCombustivel;
import com.example.demo.repository.CompraCombustivelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraCombustivelService {

    private final CompraCombustivelRepository compraCombustivelRepository;

    @Transactional
    public CompraCombustivel create(CompraCombustivel compraCombustivel) {
        return compraCombustivelRepository.save(compraCombustivel);
    }

    public CompraCombustivel findById(Long id) {
        return compraCombustivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra de combustível não encontrada"));
    }

    @Transactional
    public CompraCombustivel update(Long id, CompraCombustivel compraCombustivel) {
        CompraCombustivel existingCompra = findById(id);
        existingCompra.setFornecedor(compraCombustivel.getFornecedor());
        existingCompra.setLitros(compraCombustivel.getLitros());
        existingCompra.setPrecoLitro(compraCombustivel.getPrecoLitro());
        existingCompra.setPrecoTotal(compraCombustivel.getPrecoTotal());
        existingCompra.setDataCompra(compraCombustivel.getDataCompra());
        return compraCombustivelRepository.save(existingCompra);
    }

    @Transactional
    public void delete(Long id) {
        compraCombustivelRepository.deleteById(id);
    }
}