package br.com.easygo.cliente.model;

import java.util.List;

public class Garcom implements Comparable<Garcom>{

    private int id;
    private String matricula;
    private String nome;
    private List<ItemPedido> listaItensPedidos;
    private List<Pedido> listaPedidos;
    private List<Solicitacao> listaSolicitacoes;

    public Garcom(int id, String matricula, String nome, List<ItemPedido> listaItensPedidos, List<Pedido> listaPedidos, List<Solicitacao> solicitacoes) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.listaItensPedidos = listaItensPedidos;
        this.listaPedidos = listaPedidos;
        this.listaSolicitacoes = solicitacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemPedido> getListaItensPedidos() {
        return listaItensPedidos;
    }

    public void setListaItensPedidos(List<ItemPedido> listaItensPedidos) {
        this.listaItensPedidos = listaItensPedidos;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Solicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    @Override
    public String toString() {
        return "[Garcom]" + "\n" +
                "codigo: " + id + "\n" +
                "matricula: " + matricula + "\n" +
                "nome: " + nome + "\n";
    }

    @Override
    public int compareTo(Garcom o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public boolean equals(Object obj) {
        Garcom o = (Garcom) obj;
        return this.id == o.id && this.matricula.equals(o.matricula) && this.nome.equals(o.nome) && this.listaItensPedidos.equals(o.listaItensPedidos) && this.listaPedidos.equals(o.listaPedidos) && this.listaSolicitacoes.equals(o.listaSolicitacoes);
    }
}
