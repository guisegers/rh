create table funcionario (
    cpf varchar(14) not null,
	nome varchar(200),
	cargo varchar(50),
	salario float not null,
	data_ultimo_reajuste date,
    PRIMARY KEY (cpf)
);