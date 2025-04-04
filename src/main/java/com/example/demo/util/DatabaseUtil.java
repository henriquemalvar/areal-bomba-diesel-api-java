package com.example.demo.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUtil {

    private final DataSource dataSource;

    public DatabaseUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void cleanDatabase() {
        try {
            String sql = new String(Files.readAllBytes(
                    new ClassPathResource("db/clean.sql").getFile().toPath()),
                    StandardCharsets.UTF_8);

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/clean.sql"));
            populator.execute(dataSource);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao limpar o banco de dados", e);
        }
    }

    public void seedDatabase() {
        try {
            String sql = new String(Files.readAllBytes(
                    new ClassPathResource("db/seed.sql").getFile().toPath()),
                    StandardCharsets.UTF_8);

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/seed.sql"));
            populator.execute(dataSource);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao popular o banco de dados", e);
        }
    }
}