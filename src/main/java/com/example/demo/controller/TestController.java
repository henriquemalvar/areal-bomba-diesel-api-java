package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@GetMapping
	public String testEndpoint() {
		return "Hoje é sexta, acaba logo semana!";
	}
	
	@PostMapping
	public ResponseEntity<String> createResource(@RequestBody String requestBody){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Criado com sucesso: "+ requestBody);
	}
}
