-- comentários
-- a linha abaixo cria um banco de dados
create database dbinfox;
-- a linha abaixo escolhe o banco de dados a ser utilizado
use dbinfox;
-- o bloco de instruções abaixo cria uma tabela
create table tbusuarios(
iduser int  primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
 );
 -- o comando abaixo descreve  a tabela
 describe tbusuarios;
 -- a linha abaixo insere dados na tabela CRUD
 -- create -> insert
 insert into tbusuarios(iduser,usuario,fone,login,senha)
 values(1,'José de Assis','999-999','joseassis','123456');
 -- a linha abaixo exibe os dados da tabela
 -- read -> select
 select * from tbusuarios;
 insert into tbusuarios(iduser,usuario,fone,login,senha)
 values(2,'Administrador','999-999','admin','admin');
 insert into tbusuarios(iduser,usuario,fone,login,senha)
 values(3,'Bill Gates','999-999','bill','123456');
 -- a linha abaixo modifica dados da tabela
 -- update -> update
 update tbusuarios set  fone = '8888-8888' where iduser = 2;
 -- atualiza a tabela usuarios no campo fone onde o idsuario é 2. se eu não colocar where, altera todos os fone do bd.
 -- a linha abaixo apaga um registro da tabela
 -- delete -> delete 
 delete from tbusuarios where iduser = 3;
 -- sempre use a clausula where
 
 -- cadastro de cliente
 create table tbclientes (
 idcli int primary key auto_increment,
 nomecli varchar(50) not null,
 endci varchar(100),
 fonecli varchar(15) not null,
 email varchar(50)
 );
 
 describe tbclientes;
 
 insert into tbclientes(nomecli,endci,fonecli,email)
 values('Linus Torvalds','Rua Tux, 2015','9999-9999','linus@linux.com');
 select * from tbclientes;
 insert into tbclientes(nomecli,endci,fonecli,email)
 values('Linus Torvalds','Rua Tux, 2015','9999-9999','linus@linux.com');
 update tbclientes set nomecli ='Steve Jobs' where idcli = 2; 
 update tbclientes set endci = 'Rua MKaka, 2016', fonecli = '8888-8888', email = 'Steve@apple.com' where idcli = 2;
 
create table tbos(
os int primary key auto_increment,
data_os timestamp default current_timestamp, 
equipamento varchar(50) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2),
idcli int not null,
foreign key(idcli) references tbclientes(idcli)
);

describe tbos;
 -- gerar ordem de serviço
 insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli)
 values('Notebook','Não liga','troca da fonte','Zé',87.50,1);
 select * from tbos;
 
 -- o codigo abaixo traz informações de duas tabelas.
 select 
 O.os,equipamento,defeito,servico,valor, 
 C.nomecli,fonecli
 from tbos as O
 inner join tbclientes as C
 on(O.idcli = C.idcli);
 -- select bem especial que une o conteudo de duas tabelas
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 