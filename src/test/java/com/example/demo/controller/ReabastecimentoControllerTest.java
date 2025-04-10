package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.entity.Bomba;
import com.example.demo.model.entity.Maquinario;
import com.example.demo.model.entity.Reabastecimento;
import com.example.demo.model.entity.Usuario;
import com.example.demo.service.ReabastecimentoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReabastecimentoController.class)
class ReabastecimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReabastecimentoService reabastecimentoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reabastecimento reabastecimento;
    private Maquinario maquinario;
    private Usuario usuario;
    private Bomba bomba;

    @BeforeEach
    void setUp() {
        maquinario = new Maquinario();
        maquinario.setId(1L);
        maquinario.setNome("Trator Teste");
        maquinario.setTipo("Trator");
        maquinario.setCodigoBomba("BOMBA001");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste@email.com");
        usuario.setCpf("12345678900");

        bomba = new Bomba();
        bomba.setId(1L);
        bomba.setCapacidade(1000.0f);
        bomba.setLocalizacao("Pista 1");

        reabastecimento = new Reabastecimento();
        reabastecimento.setId(1L);
        reabastecimento.setMaquinario(maquinario);
        reabastecimento.setUsuario(usuario);
        reabastecimento.setLitros(50.0f);
        reabastecimento.setData(LocalDateTime.now());
        reabastecimento.setBomba(bomba);
    }

    @Test
    void create_DeveCriarReabastecimentoComSucesso() throws Exception {
        when(reabastecimentoService.create(any(Reabastecimento.class))).thenReturn(reabastecimento);

        mockMvc.perform(post("/api/reabastecimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reabastecimento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(reabastecimento.getId()))
                .andExpect(jsonPath("$.maquinario.id").value(reabastecimento.getMaquinario().getId()))
                .andExpect(jsonPath("$.usuario.id").value(reabastecimento.getUsuario().getId()))
                .andExpect(jsonPath("$.litros").value(reabastecimento.getLitros()))
                .andExpect(jsonPath("$.bomba.id").value(reabastecimento.getBomba().getId()));

        verify(reabastecimentoService, times(1)).create(any(Reabastecimento.class));
    }

    @Test
    void findAll_DeveRetornarTodosReabastecimentos() throws Exception {
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoService.findAll(any(), any(), any())).thenReturn(reabastecimentos);

        mockMvc.perform(get("/api/reabastecimentos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(reabastecimento.getId()))
                .andExpect(jsonPath("$[0].maquinario.id").value(reabastecimento.getMaquinario().getId()))
                .andExpect(jsonPath("$[0].usuario.id").value(reabastecimento.getUsuario().getId()))
                .andExpect(jsonPath("$[0].litros").value(reabastecimento.getLitros()))
                .andExpect(jsonPath("$[0].bomba.id").value(reabastecimento.getBomba().getId()));

        verify(reabastecimentoService, times(1)).findAll(null, null, null);
    }

    @Test
    void findAll_DeveRetornarReabastecimentosComFiltros() throws Exception {
        LocalDate dataInicio = LocalDate.now().minusDays(7);
        LocalDate dataFim = LocalDate.now();
        Long maquinarioId = 1L;
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoService.findAll(dataInicio, dataFim, maquinarioId)).thenReturn(reabastecimentos);

        mockMvc.perform(get("/api/reabastecimentos")
                .param("dataInicio", dataInicio.toString())
                .param("dataFim", dataFim.toString())
                .param("maquinarioId", maquinarioId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(reabastecimento.getId()));

        verify(reabastecimentoService, times(1)).findAll(dataInicio, dataFim, maquinarioId);
    }

    @Test
    void findById_DeveRetornarReabastecimentoQuandoExistir() throws Exception {
        when(reabastecimentoService.findById(1L)).thenReturn(reabastecimento);

        mockMvc.perform(get("/api/reabastecimentos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(reabastecimento.getId()))
                .andExpect(jsonPath("$.maquinario.id").value(reabastecimento.getMaquinario().getId()))
                .andExpect(jsonPath("$.usuario.id").value(reabastecimento.getUsuario().getId()))
                .andExpect(jsonPath("$.litros").value(reabastecimento.getLitros()))
                .andExpect(jsonPath("$.bomba.id").value(reabastecimento.getBomba().getId()));

        verify(reabastecimentoService, times(1)).findById(1L);
    }

    @Test
    void update_DeveAtualizarReabastecimentoComSucesso() throws Exception {
        when(reabastecimentoService.update(anyLong(), any(Reabastecimento.class))).thenReturn(reabastecimento);

        mockMvc.perform(put("/api/reabastecimentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reabastecimento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reabastecimento.getId()))
                .andExpect(jsonPath("$.maquinario.id").value(reabastecimento.getMaquinario().getId()))
                .andExpect(jsonPath("$.usuario.id").value(reabastecimento.getUsuario().getId()))
                .andExpect(jsonPath("$.litros").value(reabastecimento.getLitros()))
                .andExpect(jsonPath("$.bomba.id").value(reabastecimento.getBomba().getId()));

        verify(reabastecimentoService, times(1)).update(anyLong(), any(Reabastecimento.class));
    }

    @Test
    void delete_DeveDeletarReabastecimentoComSucesso() throws Exception {
        doNothing().when(reabastecimentoService).delete(1L);

        mockMvc.perform(delete("/api/reabastecimentos/1"))
                .andExpect(status().isNoContent());

        verify(reabastecimentoService, times(1)).delete(1L);
    }
}