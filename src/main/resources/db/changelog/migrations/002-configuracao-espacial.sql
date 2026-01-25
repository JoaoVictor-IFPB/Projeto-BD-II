--liquibase formatted sql

--changeset joao:ativar-postgis
CREATE EXTENSION IF NOT EXISTS postgis;

--changeset joao:criar-tabela-enderecos-espaciais
CREATE TABLE enderecos_clientes (
    id SERIAL PRIMARY KEY,
    cliente_nome VARCHAR(100),
    logradouro VARCHAR(255),
    -- O tipo GEOGRAPHY(POINT, 4326) usa latitude/longitude reais (WGS84)
    localizacao GEOGRAPHY(POINT, 4326)
);

--changeset joao:adicionar-coluna-espacial-usuario
ALTER TABLE usuarios ADD COLUMN localizacao geography(Point, 4326);

--changeset joao:index-espacial-usuario
CREATE INDEX idx_usuarios_localizacao ON usuarios USING GIST (localizacao);

--changeset joao:criar-indice-espacial
-- Índices GIST tornam as buscas por distância extremamente rápidas
CREATE INDEX idx_cliente_localizacao ON enderecos_clientes USING GIST (localizacao);
