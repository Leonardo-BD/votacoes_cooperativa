CREATE SEQUENCE voto_id_seq;

CREATE TABLE voto (
    id INTEGER DEFAULT NEXTVAL('voto_id_seq') NOT NULL,
    id_sessao INTEGER NOT NULL REFERENCES sessao(id),
    id_associado INTEGER NOT NULL REFERENCES associado(id),
    valor BOOLEAN NOT NULL,
	data_insercao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE(id_sessao, id_associado)
);