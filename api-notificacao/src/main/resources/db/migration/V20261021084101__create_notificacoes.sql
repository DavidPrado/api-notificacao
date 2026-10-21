CREATE TABLE notificacoes
(
    id           UUID PRIMARY KEY,
    destinatario VARCHAR(255) NOT NULL,
    mensagem     VARCHAR(500) NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    tentativas   INTEGER      NOT NULL DEFAULT 0,
    criado_em    TIMESTAMP    NOT NULL
);