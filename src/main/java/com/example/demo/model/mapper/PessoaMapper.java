package com.example.demo.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.NovaPessoaDTO;
import com.example.demo.model.dto.PessoaDTO;
import com.example.demo.model.entity.Pessoa;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PessoaMapper {

	private final ModelMapper modelMapper;

	public Pessoa toPessoa(NovaPessoaDTO novaPessoaDTO) {
		return Pessoa.builder()
				.cpf(novaPessoaDTO.getCpf())
				.dataNascimento(novaPessoaDTO.getDataNascimento())
				.email(novaPessoaDTO.getEmail())
				.nome(novaPessoaDTO.getNome())
				.telefone(novaPessoaDTO.getTelefone())
				.build();
	}

	public PessoaDTO toPessoaDTO(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaDTO.class);
	}
}
