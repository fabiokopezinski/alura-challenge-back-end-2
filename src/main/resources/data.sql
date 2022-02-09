DROP TABLE IF EXISTS tb_despesas;
DROP TABLE IF EXISTS tb_receitas;
DROP TABLE IF EXISTS tb_user_profile;
DROP TABLE IF EXISTS tb_profile;
DROP TABLE IF EXISTS tb_user;

create table tb_despesas(
    id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data DATE,
    categoria CHARACTER VARYING,
    PRIMARY KEY(id)
);

create table tb_receitas(
    id SERIAL NOT NULL ,
    descricao CHARACTER VARYING(255),
    valor NUMERIC(12,3),
    data CHARACTER VARYING,
    PRIMARY KEY(id)
);

create table tb_profile(
    profile_id SERIAL NOT NULL,
    name_profile CHARACTER VARYING,
    primary key(profile_id)
);

create table tb_user(
    user_id SERIAL NOT NULL,
    username CHARACTER VARYING(255) NOT NULL,
    password_user CHARACTER VARYING(255) NOT NULL,
    email CHARACTER VARYING(255) NOT NULL,
    primary key(user_id)
);


create table tb_user_profile(
     user_id SERIAL NOT NULL,
     profile_id SERIAL NOT NULL
);

insert into tb_profile values(1,'ADMIN');

insert into tb_profile values(2,'USER');

insert into tb_user values(1,'admin','$2y$12$aNK.FURoeGsUS6EfGaVr.OPNR1R46EDlFiYAfwQsvDc8p7rrQrUHe','admin@email.com');

insert into tb_user_profile values(1,1);
