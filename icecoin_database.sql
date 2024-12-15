CREATE DATABASE IF NOT EXISTS icecoin_db;
USE icecoin_db;

CREATE TABLE if NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if NOT EXISTS contas (
    id_conta INT AUTO_INCREMENT PRIMARY KEY,
    endereco TEXT NOT NULL,
    id_usuario INT NOT NULL,
    saldo DOUBLE DEFAULT 0,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE if NOT EXISTS transacoes (
    id_transacao INT AUTO_INCREMENT PRIMARY KEY,
    enderecoRemetente VARCHAR(255) NOT NULL,
    enderecoDestinatario VARCHAR(255) NOT NULL,
    valor DOUBLE NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if NOT EXISTS blockchain (
    id_bloco INT AUTO_INCREMENT PRIMARY KEY,
    hash_bloco_anterior VARCHAR(255) NOT NULL,
    hash_bloco VARCHAR(255) NOT NULL,
    remetente VARCHAR(255),
    destinatario VARCHAR(255),
    valor DOUBLE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if NOT EXISTS historico (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    valor DOUBLE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);