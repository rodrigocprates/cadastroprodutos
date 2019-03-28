package br.com.curso.exemplos;

import br.com.curso.dao.CategoriaDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.jdbc.ConnectionPool;
import br.com.curso.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExemploBuscarProdutos {

    public static void main(String[] args) throws SQLException {
        Connection conexao = new ConnectionPool("jdbc:h2:~/test", "sa", "").getConexao();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexao);

        // Buscar
        List<Produto> produtos = new ProdutoDAO(conexao, categoriaDAO).buscar();
        System.out.println(produtos);

    }

}
