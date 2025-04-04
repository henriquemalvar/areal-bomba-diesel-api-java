package com.example.demo.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.FuncaoDTO;
import com.example.demo.model.entity.Funcao;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FuncaoMapper {

    private final ModelMapper modelMapper;

    public FuncaoDTO toFuncaoDTO(Funcao funcao) {
        return modelMapper.map(funcao, FuncaoDTO.class);
    }
}