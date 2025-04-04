package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.DatabaseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/database")
@Tag(name = "Banco de Dados", description = "Endpoints para gerenciamento do banco de dados")
public class DatabaseController {

    private final DatabaseUtil databaseUtil;

    public DatabaseController(DatabaseUtil databaseUtil) {
        this.databaseUtil = databaseUtil;
    }

    @PostMapping("/clean")
    @Operation(summary = "Limpar banco de dados", description = "Remove todos os dados das tabelas do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banco de dados limpo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> cleanDatabase() {
        databaseUtil.cleanDatabase();
        return ResponseEntity.ok("Banco de dados limpo com sucesso");
    }

    @PostMapping("/seed")
    @Operation(summary = "Popular banco de dados", description = "Insere dados de exemplo no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banco de dados populado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> seedDatabase() {
        databaseUtil.seedDatabase();
        return ResponseEntity.ok("Banco de dados populado com sucesso");
    }
}