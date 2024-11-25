use icecoin_db;
-- Cria o banco de dados caso nao exista
CREATE DATABASE IF NOT EXISTS icecoin_db;

-- Usa o banco de dados
USE icecoin_db;

-- Tabela: usuarios
CREATE TABLE if NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela: contas
CREATE TABLE if NOT EXISTS contas (
    id_conta INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    saldo DOUBLE DEFAULT 0,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabela: transacoes
CREATE TABLE if NOT EXISTS transacoes (
    id_transacao INT AUTO_INCREMENT PRIMARY KEY,
    id_remetente INT NOT NULL,
    id_destinatario INT NOT NULL,
    valor DOUBLE NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_remetente) REFERENCES contas(id_conta),
    FOREIGN KEY (id_destinatario) REFERENCES contas(id_conta)
);

-- Tabela: blockchain
CREATE TABLE if NOT EXISTS blockchain (
    id_bloco INT AUTO_INCREMENT PRIMARY KEY,
    hash_bloco_anterior VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela: transacoes_bloco
CREATE TABLE if NOT EXISTS transacoes_bloco (
    id_bloco INT NOT NULL,
    id_transacao INT NOT NULL,
    PRIMARY KEY (id_bloco, id_transacao),
    FOREIGN KEY (id_bloco) REFERENCES blockchain(id_bloco),
    FOREIGN KEY (id_transacao) REFERENCES transacoes(id_transacao)
);

-- Tabela: icecoin
CREATE TABLE if NOT EXISTS icecoin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL,
    quantidade DOUBLE NOT NULL,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Adding the enderecos table
CREATE TABLE if NOT EXISTS enderecos (
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    id_conta INT NOT NULL,
    endereco VARCHAR(255) NOT NULL UNIQUE,
    chave_publica TEXT NOT NULL,
    chave_privada TEXT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_conta) REFERENCES contas(id_conta)
);