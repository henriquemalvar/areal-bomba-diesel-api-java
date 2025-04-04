package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NovaPessoaDTO;
import com.example.demo.model.dto.PessoaDTO;
import com.example.demo.model.entity.Pessoa;
import com.example.demo.model.mapper.PessoaMapper;
import com.example.demo.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper;
	
	public PessoaDTO create(NovaPessoaDTO novaPessoaDTO) {
		Pessoa pessoa = pessoaRepository.save(pessoaMapper.toPessoa(novaPessoaDTO));
		return pessoaMapper.toPessoaDTO(pessoa);
	}
}
