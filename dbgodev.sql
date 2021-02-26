-- comentários
-- A linha abaixo cria um BD
create database dbgodev;
-- A linha abaixo escolhe o banco de dados utilisado
use dbgodev;
-- o bloco abaixo cria uma tabela
create table tbusuarios(
iduser int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);
-- o comando descreve a tabela
describe tbusuarios;
-- a linha abaixo insere dados na tabela (CRUD)
-- CREATE -- insert
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(1,'Allan Siqueira','9999-9999','allansiqueira','123456');
-- a linha abaixo exibe os dados da tabela(CRUD)
-- read -- select
select*from tbusuarios
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(2,'Administrador','9999-9999','admin','admin');

-- a linha abaixo modifica dados na tabela(CRUD)
-- update -- update
update tbusuarios set fone='8888-8888' where iduser=2;

-- a linha abaixo apaga um registro da tabela (CRUD)
-- delete
delete from tbusuarios where iduser =1;

-- Criando a tabela de Clientes/alunos
create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
sobrenome varchar(100) not null,
sala1 varchar(50) not null,
sala2 varchar(50) not null,
cafe varchar(50) not null
);

describe tbclientes;

insert into tbclientes(nomecli,sobrenome,sala1,sala2,cafe)
values('Anderson','Spider Silva','38','45','espaço chocolate');

select*from tbclientes;

-- criando tabela de salas
create table tbsalas(
idsala int primary key auto_increment,
nomesala varchar(50) not null,
lotacao varchar(100) not null
);

describe tbsalas;

insert into tbsalas(nomesala,lotacao)
values('38','100 pessoas');

select*from tbsalas;

-- criando tabela espaço café
create table tbcafe(
idcafe int primary key auto_increment,
nomeespcafe varchar(50) not null,
lotacaoespcafe varchar(100) not null
);

describe tbcafe;

insert into tbcafe(nomeespcafe,lotacaoespcafe)
values('espaço chocolate','50 pessoas');

select*from tbcafe;

-- o cod abaixo traz informações das tabelas do sistema
select
P.nomecli,sobrenome,sala1,sala2,cafe
from tbclientes as P;
