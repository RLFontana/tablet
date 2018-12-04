package br.com.easygo.cliente.adapters.objects;

import java.util.List;

import br.com.easygo.cliente.model.Produto;

public class ProdutoAdapterObject {

    private String tipo;
    private List<Produto> produtos;
    private boolean expanded;

    public ProdutoAdapterObject(String tipo, List<Produto> produtos) {
        this.tipo = tipo;
        this.produtos = produtos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
