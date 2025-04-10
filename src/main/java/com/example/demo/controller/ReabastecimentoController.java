package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Reabastecimento;
import com.example.demo.service.ReabastecimentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reabastecimentos")
@RequiredArgsConstructor
@Tag(name = "Reabastecimentos", description = "API para gerenciamento de reabastecimentos")
public class ReabastecimentoController {

    private final ReabastecimentoService reabastecimentoService;

    @PostMapping
    @Operation(summary = "Criar um novo reabastecimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reabastecimento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Reabastecimento> create(@RequestBody Reabastecimento reabastecimento) {
        Reabastecimento reabastecimentoCriado = reabastecimentoService.create(reabastecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(reabastecimentoCriado);
    }

    @GetMapping
    @Operation(summary = "Buscar reabastecimentos com filtros opcionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reabastecimentos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<Reabastecimento>> findAll(
            @Parameter(description = "Data inicial (formato: yyyy-MM-dd)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @Parameter(description = "Data final (formato: yyyy-MM-dd)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @Parameter(description = "ID do maquinário") @RequestParam(required = false) Long maquinarioId) {
        List<Reabastecimento> reabastecimentos = reabastecimentoService.findAll(dataInicio, dataFim, maquinarioId);
        return ResponseEntity.ok(reabastecimentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reabastecimento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reabastecimento encontrado"),
            @ApiResponse(responseCode = "404", description = "Reabastecimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Reabastecimento> findById(@PathVariable Long id) {
        Reabastecimento reabastecimento = reabastecimentoService.findById(id);
        return ResponseEntity.ok(reabastecimento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um reabastecimento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reabastecimento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Reabastecimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Reabastecimento> update(@PathVariable Long id, @RequestBody Reabastecimento reabastecimento) {
        Reabastecimento reabastecimentoAtualizado = reabastecimentoService.update(id, reabastecimento);
        return ResponseEntity.ok(reabastecimentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um reabastecimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reabastecimento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reabastecimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reabastecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}