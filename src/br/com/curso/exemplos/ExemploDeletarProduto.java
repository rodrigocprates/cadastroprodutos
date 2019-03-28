package br.com.curso.exemplos;

import br.com.curso.dao.CategoriaDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ExemploDeletarProduto {

    public static void main(String[] args) throws SQLException {
        Connection conexao = new ConnectionPool("jdbc:h2:~/test", "sa", "").getConexao();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexao);

        Boolean deletar = new ProdutoDAO(conexao, categoriaDAO).deletar(2L);

        System.out.println(deletar);

    }

}
