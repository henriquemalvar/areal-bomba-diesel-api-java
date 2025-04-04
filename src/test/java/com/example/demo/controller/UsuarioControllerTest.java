package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.dto.NovoUsuarioDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void deveCriarUsuarioComSucesso() throws Exception {
        NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO();
        novoUsuarioDTO.setNome("Teste");
        novoUsuarioDTO.setEmail("teste@teste.com");
        novoUsuarioDTO.setCpf("12345678900");
        novoUsuarioDTO.setSenha("senha123");
        novoUsuarioDTO.setTelefone("11999999999");
        novoUsuarioDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        novoUsuarioDTO.setFuncaoId(1L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("Teste");
        usuarioDTO.setEmail("teste@teste.com");
        usuarioDTO.setCpf("12345678900");
        usuarioDTO.setTelefone("11999999999");
        usuarioDTO.setDataNascimento(LocalDate.of(1990, 1, 1));

        when(usuarioService.create(any(NovoUsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoUsuarioDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.email").value("teste@teste.com"));
    }

    @Test
    void deveRetornarErroQuandoDadosInvalidos() throws Exception {
        NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO();
        // Dados inválidos: todos os campos obrigatórios são nulos

        when(usuarioService.create(any(NovoUsuarioDTO.class)))
                .thenThrow(new IllegalArgumentException("Dados inválidos"));

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoUsuarioDTO)))
                .andExpect(status().isBadRequest());
    }
}