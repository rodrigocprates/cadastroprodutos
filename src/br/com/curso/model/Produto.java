package br.com.curso.model;

public class Produto {

    private Long id;
    private String descricao;
    private String categoria;

    public Produto(String descricao, String categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(Long id, String descricao, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
