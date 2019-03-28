package br.com.curso.dao;

import br.com.curso.modelo.Categoria;
import br.com.curso.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDAO {

    private Connection conexao;
    private CategoriaDAO categoriaDAO;

    public ProdutoDAO(Connection conexao, CategoriaDAO categoriaDAO) {
        this.conexao = conexao;
        this.categoriaDAO = categoriaDAO;
    }

    public Produto salvar(Produto produto) throws SQLException {
        PreparedStatement statement = null;

        try {
            conexao.setAutoCommit(Boolean.FALSE);

            // Salvar categoria
            Long idCategoria = categoriaDAO.salvar(produto.getCategoria()).getId();

            String sql = "INSERT INTO produtos (descricao, id_categoria) VALUES (?, ?)";

            statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, produto.getDescricao());
            statement.setLong(2, idCategoria);

            // Inserir novo produto
            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                long id = keys.getLong("id");
                produto.setId(id);
            }

            conexao.commit();
            statement.close();

            return produto;

        } catch (SQLException e) {
            conexao.rollback();
            throw new RuntimeException("Problemas ao executar SQL.", e);
        } finally {
            conexao.close();
        }
    }

    public Boolean deletar(long idProduto) throws SQLException {
        PreparedStatement statement = null;
        String sql = "DELETE FROM produtos WHERE id = ?";

        try {
            statement = conexao.prepareStatement(sql);
            statement.setLong(1, idProduto);
            return statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao executar SQL.", e);
        } finally {
            statement.close();
        }
    }

    public List<Produto> buscar() {
        PreparedStatement statement;

        StringBuilder sql = new StringBuilder().append("SELECT p.id idProduto, p.descricao descricaoProduto, c.id idCategoria, c.nome nomeCategoria FROM produtos p INNER JOIN categorias c ON p.id_categoria = c.id");

        try {
            statement = conexao.prepareStatement(sql.toString());

            statement.executeQuery();

            List<Produto> produtos = montarResultadoBusca(statement);

            statement.close();
            conexao.close();

            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao buscar Produtos.", e);
        }
    }

    public List<Produto> buscar(List<String> categorias) {
        PreparedStatement statement;

        StringBuilder sql = new StringBuilder()
                .append("SELECT p.id idProduto, p.descricao descricaoProduto, c.id idCategoria, c.nome nomeCategoria " +
                        "FROM produtos p INNER JOIN categorias c ON p.id_categoria = c.id " +
                        "WHERE c.nome IN %s");

        String clausulaIN = montarClausulaIN(categorias);

        String sqlFormatado = String.format(sql.toString(), clausulaIN);

        System.out.println(sqlFormatado);

        try {
            statement = conexao.prepareStatement(sqlFormatado);
            statement.executeQuery();

            List<Produto> produtos = montarResultadoBusca(statement);

            statement.close();
            conexao.close();

            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Problemas ao buscar Produtos.", e);
        }
    }

    private List<Produto> montarResultadoBusca(PreparedStatement statement) throws SQLException {
        ResultSet resultSet;
        resultSet = statement.getResultSet();
        List<Produto> produtos = new ArrayList<>();

        while (resultSet.next()) {
            Long idProduto = resultSet.getLong("idProduto");
            String descricaoProduto = resultSet.getString("descricaoProduto");

            Long idCategoria = resultSet.getLong("idCategoria");
            String nomeCategoria = resultSet.getString("nomeCategoria");

            Categoria categoria = new Categoria(idCategoria, nomeCategoria);

            produtos.add(new Produto(idProduto, descricaoProduto, categoria));
        }

        resultSet.close();

        return produtos;
    }

    private String montarClausulaIN(List<String> categorias) {
        return categorias
                .stream()
                .collect(Collectors.joining("','", "('", "')"));
    }
}
