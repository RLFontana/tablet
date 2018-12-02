package br.com.easygo.cliente.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.easygo.cliente.util.Format;

public class Produto implements Comparable<Produto>, Serializable {
    private int id;
    private int codigo;
    private String nome;
    private TipoProduto tipo;
    private String descricao;
    private BigDecimal preco;

    public Produto(int id, int codigo, String nome, TipoProduto tipo, String descricao, BigDecimal preco) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(int id, int codigo, String nome, TipoProduto tipo, String descricao, double preco) {
        this(id, codigo, nome, tipo, descricao, new BigDecimal(preco));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public double getPrecoDouble(){
        return preco.doubleValue();
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

    @Override
    public int compareTo(Produto o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public boolean equals(Object obj) {
        Produto o = (Produto) obj;
        return this.id == o.id && this.codigo == o.codigo && this.nome.equals(o.nome) && this.tipo.equals(o.tipo) && this.descricao.equals(o.descricao) && this.preco.equals(o.preco);
    }
}
