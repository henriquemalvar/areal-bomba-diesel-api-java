package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Reabastecimento;
import com.example.demo.service.ReabastecimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reabastecimentos")
@RequiredArgsConstructor
public class ReabastecimentoController {

    private final ReabastecimentoService reabastecimentoService;

    @PostMapping
    public ResponseEntity<Reabastecimento> create(@RequestBody Reabastecimento reabastecimento) {
        Reabastecimento reabastecimentoCriado = reabastecimentoService.create(reabastecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(reabastecimentoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reabastecimento> findById(@PathVariable Long id) {
        Reabastecimento reabastecimento = reabastecimentoService.findById(id);
        return ResponseEntity.ok(reabastecimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reabastecimento> update(@PathVariable Long id, @RequestBody Reabastecimento reabastecimento) {
        Reabastecimento reabastecimentoAtualizado = reabastecimentoService.update(id, reabastecimento);
        return ResponseEntity.ok(reabastecimentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reabastecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}