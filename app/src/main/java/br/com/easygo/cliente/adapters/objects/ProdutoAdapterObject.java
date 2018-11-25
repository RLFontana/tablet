package br.com.easygo.cliente.adapters.objects;

import java.util.List;

import br.com.easygo.cliente.model.Produto;

public class ProdutoAdapterObject {

    private String tipo;
    private List<Produto> produtos;
    private boolean expaded;

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

    public boolean isExpaded() {
        return expaded;
    }

    public void setExpaded(boolean expaded) {
        this.expaded = expaded;
    }
}
