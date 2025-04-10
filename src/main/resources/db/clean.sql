-- Desativa verificação de chaves estrangeiras temporariamente
SET FOREIGN_KEY_CHECKS = 0;

-- Limpa todas as tabelas
DELETE FROM reabastecimento;
DELETE FROM compra_combustivel;
DELETE FROM bomba;
DELETE FROM maquinario;
DELETE FROM endereco_fornecedor;
DELETE FROM fornecedor;
DELETE FROM endereco_usuario;
DELETE FROM usuario;
DELETE FROM funcao;

-- Reativa verificação de chaves estrangeiras
SET FOREIGN_KEY_CHECKS = 1; 