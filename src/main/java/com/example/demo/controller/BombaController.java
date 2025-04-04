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

import com.example.demo.model.entity.Bomba;
import com.example.demo.service.BombaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bombas")
@RequiredArgsConstructor
public class BombaController {

    private final BombaService bombaService;

    @PostMapping
    public ResponseEntity<Bomba> create(@RequestBody Bomba bomba) {
        Bomba bombaCriada = bombaService.create(bomba);
        return ResponseEntity.status(HttpStatus.CREATED).body(bombaCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bomba> findById(@PathVariable Long id) {
        Bomba bomba = bombaService.findById(id);
        return ResponseEntity.ok(bomba);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bomba> update(@PathVariable Long id, @RequestBody Bomba bomba) {
        Bomba bombaAtualizada = bombaService.update(id, bomba);
        return ResponseEntity.ok(bombaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bombaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}