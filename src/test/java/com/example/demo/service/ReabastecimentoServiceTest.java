package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.entity.Bomba;
import com.example.demo.model.entity.Maquinario;
import com.example.demo.model.entity.Reabastecimento;
import com.example.demo.model.entity.Usuario;
import com.example.demo.repository.ReabastecimentoRepository;

@ExtendWith(MockitoExtension.class)
class ReabastecimentoServiceTest {

    @Mock
    private ReabastecimentoRepository reabastecimentoRepository;

    @InjectMocks
    private ReabastecimentoService reabastecimentoService;

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
    void create_DeveCriarReabastecimentoComSucesso() {
        when(reabastecimentoRepository.save(any(Reabastecimento.class))).thenReturn(reabastecimento);

        Reabastecimento resultado = reabastecimentoService.create(reabastecimento);

        assertNotNull(resultado);
        assertEquals(reabastecimento.getId(), resultado.getId());
        assertEquals(reabastecimento.getMaquinario().getId(), resultado.getMaquinario().getId());
        assertEquals(reabastecimento.getUsuario().getId(), resultado.getUsuario().getId());
        assertEquals(reabastecimento.getLitros(), resultado.getLitros());
        assertEquals(reabastecimento.getData(), resultado.getData());
        assertEquals(reabastecimento.getBomba().getId(), resultado.getBomba().getId());

        verify(reabastecimentoRepository, times(1)).save(any(Reabastecimento.class));
    }

    @Test
    void findAll_DeveRetornarTodosReabastecimentos() {
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoRepository.findAll()).thenReturn(reabastecimentos);

        List<Reabastecimento> resultado = reabastecimentoService.findAll(null, null, null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(reabastecimentoRepository, times(1)).findAll();
    }

    @Test
    void findAll_DeveRetornarReabastecimentosPorPeriodo() {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(7);
        LocalDateTime dataFim = LocalDateTime.now();
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoRepository.findByDataBetween(dataInicio.toLocalDate(), dataFim.toLocalDate()))
                .thenReturn(reabastecimentos);

        List<Reabastecimento> resultado = reabastecimentoService.findAll(dataInicio.toLocalDate(),
                dataFim.toLocalDate(), null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(reabastecimentoRepository, times(1)).findByDataBetween(dataInicio.toLocalDate(), dataFim.toLocalDate());
    }

    @Test
    void findAll_DeveRetornarReabastecimentosPorMaquinario() {
        Long maquinarioId = 1L;
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoRepository.findByMaquinarioId(maquinarioId)).thenReturn(reabastecimentos);

        List<Reabastecimento> resultado = reabastecimentoService.findAll(null, null, maquinarioId);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(reabastecimentoRepository, times(1)).findByMaquinarioId(maquinarioId);
    }

    @Test
    void findAll_DeveRetornarReabastecimentosPorPeriodoEMaquinario() {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(7);
        LocalDateTime dataFim = LocalDateTime.now();
        Long maquinarioId = 1L;
        List<Reabastecimento> reabastecimentos = Arrays.asList(reabastecimento);
        when(reabastecimentoRepository.findByDataBetweenAndMaquinarioId(dataInicio.toLocalDate(), dataFim.toLocalDate(),
                maquinarioId))
                .thenReturn(reabastecimentos);

        List<Reabastecimento> resultado = reabastecimentoService.findAll(dataInicio.toLocalDate(),
                dataFim.toLocalDate(), maquinarioId);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(reabastecimentoRepository, times(1)).findByDataBetweenAndMaquinarioId(dataInicio.toLocalDate(),
                dataFim.toLocalDate(), maquinarioId);
    }

    @Test
    void findById_DeveRetornarReabastecimentoQuandoExistir() {
        when(reabastecimentoRepository.findById(1L)).thenReturn(Optional.of(reabastecimento));

        Reabastecimento resultado = reabastecimentoService.findById(1L);

        assertNotNull(resultado);
        assertEquals(reabastecimento.getId(), resultado.getId());
        verify(reabastecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_DeveLancarExcecaoQuandoNaoExistir() {
        when(reabastecimentoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reabastecimentoService.findById(1L));
        verify(reabastecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void update_DeveAtualizarReabastecimentoComSucesso() {
        when(reabastecimentoRepository.findById(1L)).thenReturn(Optional.of(reabastecimento));
        when(reabastecimentoRepository.save(any(Reabastecimento.class))).thenReturn(reabastecimento);

        Reabastecimento resultado = reabastecimentoService.update(1L, reabastecimento);

        assertNotNull(resultado);
        assertEquals(reabastecimento.getId(), resultado.getId());
        verify(reabastecimentoRepository, times(1)).findById(1L);
        verify(reabastecimentoRepository, times(1)).save(any(Reabastecimento.class));
    }

    @Test
    void update_DeveLancarExcecaoQuandoNaoExistir() {
        when(reabastecimentoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reabastecimentoService.update(1L, reabastecimento));
        verify(reabastecimentoRepository, times(1)).findById(1L);
        verify(reabastecimentoRepository, never()).save(any(Reabastecimento.class));
    }

    @Test
    void delete_DeveDeletarReabastecimentoComSucesso() {
        when(reabastecimentoRepository.existsById(1L)).thenReturn(true);

        reabastecimentoService.delete(1L);

        verify(reabastecimentoRepository, times(1)).existsById(1L);
        verify(reabastecimentoRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_DeveLancarExcecaoQuandoNaoExistir() {
        when(reabastecimentoRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> reabastecimentoService.delete(1L));
        verify(reabastecimentoRepository, times(1)).existsById(1L);
        verify(reabastecimentoRepository, never()).deleteById(any());
    }
}