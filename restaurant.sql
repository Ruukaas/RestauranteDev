create database restaurante;
use restaurante;
create table clientes(
    nome varchar(40),
    email varchar(40) unique, 
    senha varchar(16),
    telefone int(9),
    ID int(9),
    primary key (ID)
);
select * from clientes;

create table Adms (
	nome varchar(40),
    email varchar(40) unique,
    senha varchar(16) ,
    ID int(9),
    primary key (ID)
);
insert into adms (nome, email, senha, ID) values ("Lucas Henrique", "lucasadm@gmail.com", "12345", 999999999);
select * from adms;

create table Pedidos(
	donoDoPedido varchar(40),
    donoDoPedidoID int(9),
    prato varchar(40),
    formaDePagamento varchar(20),
    observacao varchar(100),
    ID int(9),
    primary key(ID)
);
select * from pedidos;

create table Pratos(
	nome varchar(40),
    preco float(6),
    descricao varchar(60),
    ID int(9),
    primary key (ID)
);
select * from pratos;

create table formaDePagamentos (
	nome varchar(40) unique,
    ID int(3) auto_increment,
    primary key (ID)
);
select * from formaDePagamentos;











