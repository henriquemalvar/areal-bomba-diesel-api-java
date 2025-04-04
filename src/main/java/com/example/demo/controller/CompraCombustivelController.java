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

import com.example.demo.model.entity.CompraCombustivel;
import com.example.demo.service.CompraCombustivelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compras-combustivel")
@RequiredArgsConstructor
public class CompraCombustivelController {

    private final CompraCombustivelService compraCombustivelService;

    @PostMapping
    public ResponseEntity<CompraCombustivel> create(@RequestBody CompraCombustivel compraCombustivel) {
        CompraCombustivel compraCriada = compraCombustivelService.create(compraCombustivel);
        return ResponseEntity.status(HttpStatus.CREATED).body(compraCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraCombustivel> findById(@PathVariable Long id) {
        CompraCombustivel compra = compraCombustivelService.findById(id);
        return ResponseEntity.ok(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraCombustivel> update(@PathVariable Long id,
            @RequestBody CompraCombustivel compraCombustivel) {
        CompraCombustivel compraAtualizada = compraCombustivelService.update(id, compraCombustivel);
        return ResponseEntity.ok(compraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraCombustivelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}