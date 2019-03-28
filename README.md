## Run Server

> cd .

> java -cp h2*.jar org.h2.tools.Server

> Acesso: http://localhost:8082 > Connect

### Criar Tabela produtos:

> CREATE TABLE CATEGORIAS (ID INT PRIMARY KEY AUTO_INCREMENT, NOME VARCHAR(500));

> CREATE TABLE PRODUTOS (
    ID INT PRIMARY KEY AUTO_INCREMENT, 
    DESCRICAO VARCHAR(500), 
    ID_CATEGORIA VARCHAR(100),
    FOREIGN KEY (ID_CATEGORIA) references CATEGORIAS(ID)
);

## App

> Adicionar jar do H2 no classpath

> Executar classes "Main" do pacote "exemplos"
