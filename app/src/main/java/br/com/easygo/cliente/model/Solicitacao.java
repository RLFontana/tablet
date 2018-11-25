package br.com.easygo.cliente.model;

import java.util.List;

public class Solicitacao {

    private int codigo;
    private Comanda comanda;
    private List<Pedido> pedidos;
    private Garcom garcom;
    private boolean atendida;

    public Solicitacao(int codigo, Comanda comanda,  Garcom garcom) {
        this.codigo = codigo;
        this.comanda = comanda;
        this.garcom = garcom;
    }
    public Solicitacao(int codigo, Comanda comanda,  Garcom garcom, boolean atendida) {
        this.codigo = codigo;
        this.comanda = comanda;
        this.garcom = garcom;
        this.atendida = atendida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }
}
