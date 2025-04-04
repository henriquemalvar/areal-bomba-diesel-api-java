package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.NovaPessoaDTO;
import com.example.demo.model.dto.PessoaDTO;
import com.example.demo.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

	private final PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<PessoaDTO> create(@RequestBody NovaPessoaDTO pessoa){
		PessoaDTO pessoaCriada = pessoaService.create(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pessoaCriada);
	}
}
