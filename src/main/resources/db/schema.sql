-- Criação das tabelas
CREATE TABLE IF NOT EXISTS funcao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    data_nascimento DATE,
    funcao_id BIGINT,
    FOREIGN KEY (funcao_id) REFERENCES funcao(id)
);

CREATE TABLE IF NOT EXISTS endereco_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(9),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS fornecedor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS endereco_fornecedor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(9),
    fornecedor_id BIGINT,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);

CREATE TABLE IF NOT EXISTS bomba (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE,
    tipo_combustivel VARCHAR(20) NOT NULL,
    capacidade DECIMAL(10,2) NOT NULL,
    quantidade_atual DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS maquinario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    modelo VARCHAR(100),
    placa VARCHAR(20) NOT NULL UNIQUE,
    capacidade_tanque DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS compra_combustivel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_compra DATE NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    fornecedor_id BIGINT,
    bomba_id BIGINT,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id),
    FOREIGN KEY (bomba_id) REFERENCES bomba(id)
);

CREATE TABLE IF NOT EXISTS reabastecimento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_reabastecimento DATE NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    maquinario_id BIGINT,
    bomba_id BIGINT,
    FOREIGN KEY (maquinario_id) REFERENCES maquinario(id),
    FOREIGN KEY (bomba_id) REFERENCES bomba(id)
); 