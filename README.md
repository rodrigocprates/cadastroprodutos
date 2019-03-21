## Run Server

> cd .

> java -cp h2*.jar org.h2.tools.Server

> Acesso: http://localhost:8082 > Connect

### Criar Tabela produtos:

> CREATE TABLE PRODUTOS (ID INT PRIMARY KEY, DESCRICAO VARCHAR(500), CATEGORIA VARCHAR(100));

## App

> Adicionar jar do H2 no classpath

> Run Main.java

## TODO

- Exemplo commit/rollback