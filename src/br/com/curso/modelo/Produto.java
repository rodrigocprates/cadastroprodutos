package br.com.curso.modelo;

public class Produto {

    private Long id;
    private String descricao;
    private Categoria categoria;

    public Produto(String descricao, Categoria categoria) {
        this(null, descricao, categoria);
    }

    public Produto(Long id, String descricao, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
