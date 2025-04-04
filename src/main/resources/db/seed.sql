-- Inserção de Funções
INSERT INTO funcao (nome, descricao) VALUES
('ADMIN', 'Administrador do sistema');
INSERT INTO funcao (nome, descricao) VALUES
('OPERADOR', 'Operador de bomba');
INSERT INTO funcao (nome, descricao) VALUES
('GERENTE', 'Gerente do posto');

-- Inserção de Pessoas
INSERT INTO pessoa (nome, cpf, telefone, email) VALUES
('João Silva', '123.456.789-00', '(11) 99999-9999', 'joao.silva@email.com');
INSERT INTO pessoa (nome, cpf, telefone, email) VALUES
('Maria Santos', '987.654.321-00', '(11) 88888-8888', 'maria.santos@email.com');
INSERT INTO pessoa (nome, cpf, telefone, email) VALUES
('Pedro Oliveira', '456.789.123-00', '(11) 77777-7777', 'pedro.oliveira@email.com');

-- Inserção de Usuários
INSERT INTO usuario (login, senha, pessoa_id, funcao_id) VALUES
('joao.silva', '$2a$10$X7UrE2JvP3QZqX5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X', 1, 1);
INSERT INTO usuario (login, senha, pessoa_id, funcao_id) VALUES
('maria.santos', '$2a$10$X7UrE2JvP3QZqX5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X', 2, 2);
INSERT INTO usuario (login, senha, pessoa_id, funcao_id) VALUES
('pedro.oliveira', '$2a$10$X7UrE2JvP3QZqX5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X5X', 3, 3);

-- Inserção de Endereços de Usuários
INSERT INTO endereco_usuario (rua, numero, complemento, bairro, cidade, estado, cep, usuario_id) VALUES
('Rua A', '123', 'Apto 101', 'Centro', 'São Paulo', 'SP', '01234-567', 1);
INSERT INTO endereco_usuario (rua, numero, complemento, bairro, cidade, estado, cep, usuario_id) VALUES
('Rua B', '456', 'Casa', 'Jardim', 'São Paulo', 'SP', '04567-890', 2);
INSERT INTO endereco_usuario (rua, numero, complemento, bairro, cidade, estado, cep, usuario_id) VALUES
('Rua C', '789', 'Sala 202', 'Vila', 'São Paulo', 'SP', '07890-123', 3);

-- Inserção de Fornecedores
INSERT INTO fornecedor (nome, cnpj, telefone, email) VALUES
('Fornecedor A', '12.345.678/0001-90', '(11) 3333-3333', 'fornecedor.a@email.com');
INSERT INTO fornecedor (nome, cnpj, telefone, email) VALUES
('Fornecedor B', '98.765.432/0001-10', '(11) 4444-4444', 'fornecedor.b@email.com');

-- Inserção de Endereços de Fornecedores
INSERT INTO endereco_fornecedor (rua, numero, complemento, bairro, cidade, estado, cep, fornecedor_id) VALUES
('Rua X', '100', 'Galpão', 'Industrial', 'São Paulo', 'SP', '11111-111', 1);
INSERT INTO endereco_fornecedor (rua, numero, complemento, bairro, cidade, estado, cep, fornecedor_id) VALUES
('Rua Y', '200', 'Depósito', 'Comercial', 'São Paulo', 'SP', '22222-222', 2);

-- Inserção de Bombas
INSERT INTO bomba (numero, tipo_combustivel, capacidade, quantidade_atual) VALUES
(1, 'GASOLINA', 1000.00, 800.00);
INSERT INTO bomba (numero, tipo_combustivel, capacidade, quantidade_atual) VALUES
(2, 'ETANOL', 1000.00, 750.00);
INSERT INTO bomba (numero, tipo_combustivel, capacidade, quantidade_atual) VALUES
(3, 'DIESEL', 2000.00, 1500.00);

-- Inserção de Maquinários
INSERT INTO maquinario (nome, modelo, placa, capacidade_tanque) VALUES
('Trator 1', 'Modelo A', 'ABC1234', 100.00);
INSERT INTO maquinario (nome, modelo, placa, capacidade_tanque) VALUES
('Colheitadeira 1', 'Modelo B', 'XYZ5678', 150.00);
INSERT INTO maquinario (nome, modelo, placa, capacidade_tanque) VALUES
('Trator 2', 'Modelo C', 'DEF9012', 120.00);

-- Inserção de Compras de Combustível
INSERT INTO compra_combustivel (data_compra, quantidade, valor_unitario, valor_total, fornecedor_id, bomba_id) VALUES
('2024-04-04', 1000.00, 5.50, 5500.00, 1, 1);
INSERT INTO compra_combustivel (data_compra, quantidade, valor_unitario, valor_total, fornecedor_id, bomba_id) VALUES
('2024-04-04', 800.00, 4.50, 3600.00, 2, 2);
INSERT INTO compra_combustivel (data_compra, quantidade, valor_unitario, valor_total, fornecedor_id, bomba_id) VALUES
('2024-04-04', 1500.00, 6.00, 9000.00, 1, 3);

-- Inserção de Reabastecimentos
INSERT INTO reabastecimento (data_reabastecimento, quantidade, valor_total, maquinario_id, bomba_id) VALUES
('2024-04-04', 50.00, 275.00, 1, 1);
INSERT INTO reabastecimento (data_reabastecimento, quantidade, valor_total, maquinario_id, bomba_id) VALUES
('2024-04-04', 75.00, 337.50, 2, 2);
INSERT INTO reabastecimento (data_reabastecimento, quantidade, valor_total, maquinario_id, bomba_id) VALUES
('2024-04-04', 100.00, 600.00, 3, 3); 