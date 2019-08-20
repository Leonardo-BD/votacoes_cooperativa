CREATE TABLE resultado_votacao (
    id SERIAL NOT NULL,
    id_pauta INTEGER UNIQUE NOT NULL REFERENCES pauta(id),
    votos_favoraveis INTEGER NOT NULL,
    votos_contrarios INTEGER NOT NULL,
    aprovada BOOLEAN NOT NULL,
	data_publicacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);