package br.com.curso;

import br.com.curso.dao.ProdutoDAO;
import br.com.curso.jdbc.ConnectionPool;
import br.com.curso.model.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        // Salvar
        Produto prod = new Produto("Mp3 Player", "Eletrônicos");
        Produto produtoSalvo = new ProdutoDAO(connection).salvar(prod);
        System.out.println(produtoSalvo);

        // Buscar
        List<Produto> produtos = new ProdutoDAO(connection).buscar();
        System.out.println(produtos);

        // Buscar por Categoria
        List<Produto> produtosPorCategoria = new ProdutoDAO(connection).buscar("utils");
        System.out.println(produtosPorCategoria);

        // Deletar
        Boolean deletar = new ProdutoDAO(connection).deletar(7L);


    }
}
