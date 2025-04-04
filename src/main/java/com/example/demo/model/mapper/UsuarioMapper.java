package com.example.demo.model.mapper;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.NovoUsuarioDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.model.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ModelMapper modelMapper;
    private final FuncaoMapper funcaoMapper;
    private final EnderecoUsuarioMapper enderecoMapper;

    public Usuario toUsuario(NovoUsuarioDTO novoUsuarioDTO) {
        Usuario usuario = modelMapper.map(novoUsuarioDTO, Usuario.class);
        usuario.setCreatedAt(LocalDateTime.now());
        return usuario;
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        if (usuario.getFuncao() != null) {
            usuarioDTO.setFuncao(funcaoMapper.toFuncaoDTO(usuario.getFuncao()));
        }
        if (usuario.getEndereco() != null) {
            usuarioDTO.setEndereco(enderecoMapper.toEnderecoUsuarioDTO(usuario.getEndereco()));
        }
        return usuarioDTO;
    }
}