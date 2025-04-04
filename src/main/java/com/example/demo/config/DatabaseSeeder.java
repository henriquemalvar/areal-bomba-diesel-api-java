package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Funcao;
import com.example.demo.model.entity.Usuario;
import com.example.demo.repository.FuncaoRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UsuarioRepository usuarioRepository;
    private final FuncaoRepository funcaoRepository;

    @PostConstruct
    public void seedDatabase() {
        if (funcaoRepository.count() == 0) {
            Funcao adminFuncao = new Funcao();
            adminFuncao.setNome("Administrador");
            funcaoRepository.save(adminFuncao);

            Funcao userFuncao = new Funcao();
            userFuncao.setNome("Usuário");
            funcaoRepository.save(userFuncao);
        }

        if (usuarioRepository.count() == 0) {
            Funcao adminFuncao = funcaoRepository.findByNome("Administrador")
                    .orElseThrow(() -> new RuntimeException("Função 'Administrador' não encontrada"));

            Usuario admin = Usuario.builder()
                    .nome("Admin")
                    .email("admin@demo.com")
                    .cpf("00000000000")
                    .senha("admin123")
                    .telefone("11999999999")
                    .dataNascimento(LocalDate.of(1990, 1, 1))
                    .funcao(adminFuncao)
                    .createdAt(LocalDateTime.now())
                    .build();

            usuarioRepository.save(admin);
        }
    }
}
