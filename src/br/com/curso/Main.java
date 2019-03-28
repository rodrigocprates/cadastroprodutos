package br.com.curso;

public class Main {

    /**public static void main(String[] args) throws SQLException {
        Connection conexao = ConnectionPool.getConexao();

        // Salvar
        Produto prod = new Produto("Mp3 Player", "Eletrônicos");
        Produto produtoSalvo = new ProdutoDAO(conexao, categoriaDAO).salvar(prod);
        System.out.println(produtoSalvo);

        // Buscar
        List<Produto> produtos = new ProdutoDAO(conexao, categoriaDAO).buscar();
        System.out.println(produtos);

        // Buscar por Categoria
        List<Produto> produtosPorCategoria = new ProdutoDAO(conexao, categoriaDAO).buscar("utils");
        System.out.println(produtosPorCategoria);

        // Deletar
        Boolean deletar = new ProdutoDAO(conexao, categoriaDAO).deletar(7L);


    }**/
}
