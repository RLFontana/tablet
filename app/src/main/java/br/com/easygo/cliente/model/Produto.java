package br.com.easygo.cliente.model;

import java.math.BigDecimal;

import br.com.easygo.cliente.util.Format;

public class Produto {
    private int codigo;
    private String nome;
    private TipoProduto tipo;
    private String descricao;
    private BigDecimal preco;

    public Produto(int codigo, String nome, TipoProduto tipo, String descricao, BigDecimal preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(int codigo, String nome, TipoProduto tipo, String descricao, double preco){
        this(codigo, nome, tipo, descricao, new BigDecimal(preco));
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public double getPrecoDouble(){
        return preco.doubleValue();
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setPreco(double preco){
        this.preco = new BigDecimal(preco);
    }

    @Override
    public String toString() {
        return "[Produto]" + "\n" +
                "codigo: " + codigo + "\n" +
                "nome: " + nome + "\n" +
                "tipoProduto: " + tipo + "\n" +
                "descricao: " + descricao + "\n" +
                "preco: " + Format.price(getPrecoDouble()) + "\n";
    }
}
