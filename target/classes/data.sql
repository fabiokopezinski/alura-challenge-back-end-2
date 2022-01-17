DROP TABLE IF EXISTS tb_despesas;
DROP TABLE IF EXISTS tb_receitas;

create table tb_despesas(
    id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data CHARACTER VARYING,
    PRIMARY KEY(id)
);

create table tb_receitas(
    id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data CHARACTER VARYING,
    PRIMARY KEY(id)
);
