# Sistema de Gerenciamento de Posto de Combustível

Sistema desenvolvido para gerenciar operações de um posto de combustível, incluindo controle de bombas, abastecimentos, compras de combustível e cadastro de fornecedores.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Maven
- Lombok
- Swagger/OpenAPI 3.0

## Funcionalidades

- Gerenciamento de Usuários
- Gerenciamento de Fornecedores
- Controle de Bombas de Combustível
- Registro de Compras de Combustível
- Registro de Reabastecimentos
- Gerenciamento de Maquinários

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── controller/    # Controladores REST
│   │               ├── model/         # Entidades e DTOs
│   │               ├── repository/    # Repositórios JPA
│   │               ├── service/       # Lógica de negócios
│   │               └── util/          # Classes utilitárias
│   └── resources/
│       ├── application.properties    # Configurações
│       └── db/                      # Scripts SQL
└── test/                            # Testes unitários e de integração
```

## Como Executar

1. Clone o repositório
2. Execute o comando: `./mvnw spring-boot:run`
3. Acesse a documentação da API em: http://localhost:8080/swagger-ui.html
4. Acesse o console H2 em: http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - Username: sa
   - Password: (deixe em branco)

## Endpoints Principais

- `/api/usuarios` - Gerenciamento de usuários
- `/api/fornecedores` - Gerenciamento de fornecedores
- `/api/bombas` - Controle de bombas de combustível
- `/api/compras-combustivel` - Registro de compras de combustível
- `/api/reabastecimentos` - Registro de reabastecimentos
- `/api/maquinarios` - Gerenciamento de maquinários
- `/api/database` - Utilitários para gerenciamento do banco de dados

## Banco de Dados

O sistema utiliza o H2 Database em modo memória, com dados iniciais carregados automaticamente através dos scripts:
- `schema.sql` - Estrutura do banco de dados
- `seed.sql` - Dados iniciais para teste

## Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
http://localhost:8080/swagger-ui.html

## Desenvolvimento

Para contribuir com o projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request 