package br.com.curso.exemplos;

import br.com.curso.dao.CategoriaDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.jdbc.ConnectionPool;
import br.com.curso.modelo.Categoria;
import br.com.curso.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;

public class ExemploInserir {

    public static void main(String[] args) throws SQLException {
        Connection conexao = new ConnectionPool("jdbc:h2:~/test", "sa", "").getConexao();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexao);

        Categoria categoria = new Categoria("Eletrônicos");

        Produto prod = new Produto("Caixa Mp3", categoria);

        Produto produtoSalvo = new ProdutoDAO(conexao, categoriaDAO).salvar(prod);

        System.out.println(produtoSalvo);

    }

}
