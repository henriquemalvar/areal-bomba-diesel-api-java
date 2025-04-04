package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.entity.Maquinario;
import com.example.demo.service.MaquinarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MaquinarioController.class)
class MaquinarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MaquinarioService maquinarioService;

    @Test
    void deveCriarMaquinarioComSucesso() throws Exception {
        Maquinario maquinario = new Maquinario();
        maquinario.setNome("Trator Teste");
        maquinario.setTipo("Trator");
        maquinario.setCodigoBomba("BOMBA001");

        when(maquinarioService.create(any(Maquinario.class))).thenReturn(maquinario);

        mockMvc.perform(post("/api/maquinarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(maquinario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Trator Teste"))
                .andExpect(jsonPath("$.tipo").value("Trator"))
                .andExpect(jsonPath("$.codigoBomba").value("BOMBA001"));
    }

    @Test
    void deveBuscarMaquinarioPorIdComSucesso() throws Exception {
        Maquinario maquinario = new Maquinario();
        maquinario.setId(1L);
        maquinario.setNome("Trator Teste");
        maquinario.setTipo("Trator");
        maquinario.setCodigoBomba("BOMBA001");

        when(maquinarioService.findById(anyLong())).thenReturn(maquinario);

        mockMvc.perform(get("/api/maquinarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Trator Teste"))
                .andExpect(jsonPath("$.tipo").value("Trator"))
                .andExpect(jsonPath("$.codigoBomba").value("BOMBA001"));
    }

    @Test
    void deveAtualizarMaquinarioComSucesso() throws Exception {
        Maquinario maquinario = new Maquinario();
        maquinario.setId(1L);
        maquinario.setNome("Trator Atualizado");
        maquinario.setTipo("Trator");
        maquinario.setCodigoBomba("BOMBA001");

        when(maquinarioService.update(anyLong(), any(Maquinario.class))).thenReturn(maquinario);

        mockMvc.perform(put("/api/maquinarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(maquinario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Trator Atualizado"))
                .andExpect(jsonPath("$.tipo").value("Trator"))
                .andExpect(jsonPath("$.codigoBomba").value("BOMBA001"));
    }

    @Test
    void deveDeletarMaquinarioComSucesso() throws Exception {
        mockMvc.perform(delete("/api/maquinarios/1"))
                .andExpect(status().isNoContent());
    }
}