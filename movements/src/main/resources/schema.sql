create table public.tipo_transaccion (
       id bigint generated by default as identity,
       nombre varchar(255),
       sigla varchar(255),
       primary key (id)
);
INSERT INTO TIPO_TRANSACCION (nombre, sigla) VALUES ('Deposito', 'DEP');
INSERT INTO TIPO_TRANSACCION (nombre, sigla) VALUES ('Retiro', 'RET');