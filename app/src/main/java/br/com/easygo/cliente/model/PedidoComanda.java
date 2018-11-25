package br.com.easygo.cliente.model;

import java.util.List;

public class PedidoComanda {

    private List<Pedido> pedidos;
    private List<Comanda> comandas;

    public PedidoComanda(List<Pedido> pedidos, List<Comanda> comandas) {
        this.pedidos = pedidos;
        this.comandas = comandas;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
