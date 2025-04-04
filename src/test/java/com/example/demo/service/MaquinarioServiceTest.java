package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.entity.Maquinario;
import com.example.demo.repository.MaquinarioRepository;

@ExtendWith(MockitoExtension.class)
class MaquinarioServiceTest {

    @Mock
    private MaquinarioRepository maquinarioRepository;

    @InjectMocks
    private MaquinarioService maquinarioService;

    private Maquinario maquinario;

    @BeforeEach
    void setUp() {
        maquinario = new Maquinario();
        maquinario.setNome("Trator Teste");
        maquinario.setTipo("Trator");
        maquinario.setCodigoBomba("BOMBA001");
    }

    @Test
    void deveCriarMaquinarioComSucesso() {
        when(maquinarioRepository.existsByCodigoBomba(anyString())).thenReturn(false);
        when(maquinarioRepository.save(any(Maquinario.class))).thenReturn(maquinario);

        var resultado = maquinarioService.create(maquinario);

        assertNotNull(resultado);
        assertEquals("Trator Teste", resultado.getNome());
        assertEquals("Trator", resultado.getTipo());
        assertEquals("BOMBA001", resultado.getCodigoBomba());
        verify(maquinarioRepository).save(any(Maquinario.class));
    }

    @Test
    void deveLancarExcecaoQuandoCodigoBombaJaExiste() {
        when(maquinarioRepository.existsByCodigoBomba(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            maquinarioService.create(maquinario);
        });

        verify(maquinarioRepository, never()).save(any(Maquinario.class));
    }

    @Test
    void deveAtualizarMaquinarioComSucesso() {
        Long id = 1L;
        when(maquinarioRepository.findById(id)).thenReturn(java.util.Optional.of(maquinario));
        when(maquinarioRepository.save(any(Maquinario.class))).thenReturn(maquinario);

        maquinario.setNome("Novo Nome");
        var resultado = maquinarioService.update(id, maquinario);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        verify(maquinarioRepository).save(any(Maquinario.class));
    }

    @Test
    void deveLancarExcecaoQuandoMaquinarioNaoExiste() {
        Long id = 1L;
        when(maquinarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            maquinarioService.update(id, maquinario);
        });

        verify(maquinarioRepository, never()).save(any(Maquinario.class));
    }
}