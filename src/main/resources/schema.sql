DROP TABLE IF EXISTS VENDEDORES;

CREATE TABLE VENDEDORES (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    include_date  date not null,
    name varchar(255) not null,
    phone varchar(255) not null,
    age integer not null,
    city varchar(255) not null,
    state varchar(255) not null,
    region varchar(255) not null
);


DROP TABLE IF EXISTS ATUACAO;

CREATE TABLE ATUACAO (
    states varchar(255)  not null,
    region varchar(255) not null
);
