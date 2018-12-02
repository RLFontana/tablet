package br.com.easygo.cliente.model;

import java.io.Serializable;
import java.util.Date;

public class Solicitacao implements Comparable<Solicitacao>, Serializable {

    private Comanda comanda;
    private Mesa mesa;
    private Date dataSolicitacao;

    public Solicitacao(Comanda comanda, Mesa mesa) {
        this.comanda = comanda;
        this.mesa = mesa;
        this.dataSolicitacao = new Date(System.currentTimeMillis());
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    @Override
    public int compareTo(Solicitacao o) {
        return this.dataSolicitacao.compareTo(o.dataSolicitacao);
    }

    @Override
    public boolean equals(Object obj) {
        Solicitacao o = (Solicitacao) obj;
        return this.comanda.equals(o.comanda) && this.mesa.equals(o.mesa) && this.dataSolicitacao.equals(o.dataSolicitacao);
    }
}
