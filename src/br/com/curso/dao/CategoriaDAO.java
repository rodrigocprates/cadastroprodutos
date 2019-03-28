package br.com.curso.dao;

import br.com.curso.modelo.Categoria;

import java.sql.*;

public class CategoriaDAO {

    private Connection conexao;

    public CategoriaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Categoria salvar(Categoria categoria) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try {
            statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, categoria.getNome());
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                long id = keys.getLong("id");
                categoria.setId(id);
            }

            statement.close();
            return categoria;

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao executar SQL.", e);
        }
    }
}
