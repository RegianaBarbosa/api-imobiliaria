create table cliente (
    id integer not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) not null,        
    telefone varchar(12) not null,
    email varchar(100) not null,
    data_nasc date not null,    
    primary key (id)   
)