package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dto.NovoUsuarioDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioDTO create(NovoUsuarioDTO novoUsuarioDTO) {
        if (usuarioRepository.existsByEmail(novoUsuarioDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (usuarioRepository.existsByCpf(novoUsuarioDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Usuario usuario = usuarioMapper.toUsuario(novoUsuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioDTO(usuario);
    }
}