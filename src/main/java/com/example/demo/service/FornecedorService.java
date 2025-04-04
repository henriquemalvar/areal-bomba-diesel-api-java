package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Fornecedor;
import com.example.demo.repository.FornecedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Transactional
    public Fornecedor create(Fornecedor fornecedor) {
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new RuntimeException("CNPJ já cadastrado");
        }
        if (fornecedorRepository.existsByEmail(fornecedor.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    @Transactional
    public Fornecedor update(Long id, Fornecedor fornecedor) {
        Fornecedor existingFornecedor = findById(id);
        existingFornecedor.setNome(fornecedor.getNome());
        existingFornecedor.setCnpj(fornecedor.getCnpj());
        existingFornecedor.setEmail(fornecedor.getEmail());
        existingFornecedor.setEndereco(fornecedor.getEndereco());
        return fornecedorRepository.save(existingFornecedor);
    }

    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }
}