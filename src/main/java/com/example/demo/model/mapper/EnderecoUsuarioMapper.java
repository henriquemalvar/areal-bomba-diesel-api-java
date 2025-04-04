package com.example.demo.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.EnderecoUsuarioDTO;
import com.example.demo.model.entity.EnderecoUsuario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnderecoUsuarioMapper {

    private final ModelMapper modelMapper;

    public EnderecoUsuarioDTO toEnderecoUsuarioDTO(EnderecoUsuario endereco) {
        return modelMapper.map(endereco, EnderecoUsuarioDTO.class);
    }

    public EnderecoUsuario toEnderecoUsuario(EnderecoUsuarioDTO enderecoDTO) {
        return modelMapper.map(enderecoDTO, EnderecoUsuario.class);
    }
}