package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.dao.InMemoryDB;

public class Mesa implements Comparable<Mesa>{

    private int id;
    private int numero;
    private int quantidadeCadeira;
    private SituacaoMesa situacao;
    private List<ItemPedido> itensPedidos;

    public Mesa(int id, int numero, int quantidadeCadeira, SituacaoMesa situacao, List<ItemPedido> itensPedidos) {
        this.id = id;
        this.numero = numero;
        this.quantidadeCadeira = quantidadeCadeira;
        this.situacao = situacao;
        this.itensPedidos = itensPedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQuantidadeCadeira() {
        return quantidadeCadeira;
    }

    public void setQuantidadeCadeira(int quantidadeCadeira) {
        this.quantidadeCadeira = quantidadeCadeira;
    }

    public SituacaoMesa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoMesa situacao) {
        this.situacao = situacao;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    @Override
    public String toString() {
        return "[Mesa]" + "\n" +
                "codigo:" + numero + "\n" +
                "id: " + id + "\n" +
                "quantidadeCadeira: " + quantidadeCadeira + "\n" +
                "situacao: " + situacao + "\n";
    }

    @Override
    public int compareTo(Mesa o) {
        if (this.numero == o.numero) {
            return 0;
        } else if (this.numero > o.numero) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Mesa o = (Mesa) obj;
        return this.id == o.id && this.numero == o.numero && this.quantidadeCadeira == o.quantidadeCadeira && this.situacao.equals(o.situacao) && this.itensPedidos.equals(o.itensPedidos);
    }
}
