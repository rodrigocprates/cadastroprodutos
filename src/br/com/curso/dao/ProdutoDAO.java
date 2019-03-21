package br.com.curso.dao;

import br.com.curso.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public Produto salvar(Produto produto) {
        String sql = "INSERT INTO produtos (descricao, categoria) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getDescricao());
            statement.setString(2, produto.getCategoria());
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                long id = keys.getLong("id");
                produto.setId(id);
            }

            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao executar SQL.", e);
        }
    }

    public List<Produto> buscar() {
        return buscar(null);
    }

    public List<Produto> buscar(String filtroCategoria) {
        PreparedStatement statement;
        ResultSet resultSet;

        StringBuilder sql = new StringBuilder().append("SELECT * FROM produtos ");
        if (filtroCategoria != null)
            sql.append("WHERE categoria = ?");

        try {
            statement = connection.prepareStatement(sql.toString());

            if (filtroCategoria != null)
                statement.setString(1, filtroCategoria);

            statement.executeQuery();

            resultSet = statement.getResultSet();
            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                String categoria = resultSet.getString("categoria");
                produtos.add(new Produto(id, descricao, categoria));

            }

            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao buscar Produtos.", e);
        }
    }

    public Boolean deletar(long idProduto) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, idProduto);
            return statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao executar SQL.", e);
        }
    }
}
