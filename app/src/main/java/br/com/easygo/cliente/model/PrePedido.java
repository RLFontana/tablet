package br.com.easygo.cliente.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrePedido implements Serializable {

    private Mesa mesa;
    private List<Comanda> comandas;
    private List<ItemPedido> itensPedidos;

    public PrePedido() {
        comandas = new ArrayList<>();
        itensPedidos = new ArrayList<>();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itensPedidos.add(itemPedido);
    }

    public ItemPedido getItemPedido(int index) {
        return itensPedidos.get(index);
    }

    public void setComanda(Comanda comanda){
        this.comandas.add(comanda);
    }

    public Comanda getComanda(int index){
        return this.comandas.get(index);
    }
}
