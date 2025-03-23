# Sistema de Emissão de Ordem de Serviço

Projeto de OS para Windows, Linux ou Mac, focado na gestão de ordens de serviço, documentos que oficializam a execução de um trabalho.
![image](https://github.com/user-attachments/assets/836560ca-0508-49a1-b337-c1d0a3d0fc01)

## Autor
Guilherme Manoel Comanini Augusto

### Pré requisitos
1) Ter o Java **versão 8** instalado (necessário). 

[Download Java 8](https://www.java.com/pt-BR/)

2) Ter o Banco de Dados local baseado em **MySql** juntamente com o **Workbench**
   
[Download MySql](https://dev.mysql.com/downloads/installer/)
   
[Download Workbench](https://dev.mysql.com/downloads/workbench/)



3) No Workbench, após definir a senha para o administrador do banco de dados na instalação do MySql, defina uma nova conexão, colocando o username como "dba".
![image](https://github.com/user-attachments/assets/3964bb0d-8ae1-4714-bd31-91e0a5097676)
Teste a conexão e dê Ok.

4) Cole esses comandos Sql:
   ~~~sql
   create database dbinfox;
   use dbinfox;
   create table tbusuarios(iduser int primary key,usuario varchar(15) not null,fone varchar(15),login varchar(15) not null unique,senha varchar(250) not null,perfil varchar(20) not null);
   insert into tbusuarios(iduser,usuario,login,senha,perfil) values(1,'Administrador','admin',md5('admin'),'admin');
   create table tbclientes(idcli int primary key auto_increment,nomecli varchar(50) not null,endcli varchar(100),fonecli varchar(15) not null,emailcli varchar(50) unique);
   create table tbos(os int primary key auto_increment,data_os timestamp default current_timestamp,tipo varchar(15) not null,situacao varchar(20) not null,equipamento varchar(150) not null,defeito varchar(150),servico varchar(150),tecnico varchar(30),valor decimal(10,2),idcli int not null,foreign key(idcli) references tbclientes(idcli));
5) Após baixar o repositório, acesse **src/br/com/infox/dal/ModuloConexao.java.
   Nessa tela de código, insira em user e password respectivamente o username definido na conexão criada no Workbench e a senha do MySql. Se necessário, altere o nome do caminho da url para acesso ao Banco de Dados.
   ![image](https://github.com/user-attachments/assets/08c618db-8828-4a86-9f37-af6df25bd9cf)
6) Salve tudo, e ao iniciar a aplicação, a conexão se conectará ao banco de dados automaticamente.
![image](https://github.com/user-attachments/assets/a9fb5a62-1ff5-4b3d-bbae-5c4fb3404fea)
## Funcionalidades
O sistema possui o Cadastro de Clientes, sendo possível adicionar, consultar, atualizar e excluir clientes.
![image](https://github.com/user-attachments/assets/d0d2ab04-1545-4c13-a472-81ead141820a)
O sistema possui o Cadastro de Usuários, sendo possível atribuir funções de administrador, no qual libera novos recursos referentes a cadastro de usuários e de consulta a relatórios.
![image](https://github.com/user-attachments/assets/2eb28287-8cab-4fb9-8022-1d878a12c7b1)
O sistema possui o Cadastro de OS, sendo possível emitir uma ordem de serviõ vinculada a um cliente previamente cadastrado.
![image](https://github.com/user-attachments/assets/66a48a75-ea00-4b32-9805-b577d1e6b01f)



