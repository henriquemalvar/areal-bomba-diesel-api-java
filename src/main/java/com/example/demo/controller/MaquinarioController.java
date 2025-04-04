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

import com.example.demo.model.entity.Maquinario;
import com.example.demo.service.MaquinarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maquinarios")
@RequiredArgsConstructor
public class MaquinarioController {

    private final MaquinarioService maquinarioService;

    @PostMapping
    public ResponseEntity<Maquinario> create(@RequestBody Maquinario maquinario) {
        Maquinario maquinarioCriado = maquinarioService.create(maquinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(maquinarioCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquinario> findById(@PathVariable Long id) {
        Maquinario maquinario = maquinarioService.findById(id);
        return ResponseEntity.ok(maquinario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquinario> update(@PathVariable Long id, @RequestBody Maquinario maquinario) {
        Maquinario maquinarioAtualizado = maquinarioService.update(id, maquinario);
        return ResponseEntity.ok(maquinarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        maquinarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}