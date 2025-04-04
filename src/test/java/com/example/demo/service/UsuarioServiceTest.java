package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.dto.NovoUsuarioDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private NovoUsuarioDTO novoUsuarioDTO;
    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        novoUsuarioDTO = new NovoUsuarioDTO();
        novoUsuarioDTO.setNome("Teste");
        novoUsuarioDTO.setEmail("teste@teste.com");
        novoUsuarioDTO.setCpf("12345678900");
        novoUsuarioDTO.setSenha("senha123");
        novoUsuarioDTO.setTelefone("11999999999");
        novoUsuarioDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        novoUsuarioDTO.setFuncaoId(1L);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setCpf("12345678900");
        usuario.setSenha("senha123");
        usuario.setTelefone("11999999999");
        usuario.setDataNascimento(LocalDate.of(1990, 1, 1));

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("Teste");
        usuarioDTO.setEmail("teste@teste.com");
        usuarioDTO.setCpf("12345678900");
        usuarioDTO.setTelefone("11999999999");
        usuarioDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(false);
        when(usuarioMapper.toUsuario(any(NovoUsuarioDTO.class))).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDTO(any(Usuario.class))).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.create(novoUsuarioDTO);

        assertNotNull(resultado);
        assertEquals(novoUsuarioDTO.getNome(), resultado.getNome());
        assertEquals(novoUsuarioDTO.getEmail(), resultado.getEmail());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> usuarioService.create(novoUsuarioDTO));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> usuarioService.create(novoUsuarioDTO));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}