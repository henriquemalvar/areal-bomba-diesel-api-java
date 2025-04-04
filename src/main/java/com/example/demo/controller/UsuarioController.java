package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.NovoUsuarioDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody NovoUsuarioDTO usuario) {
        UsuarioDTO usuarioCriado = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}