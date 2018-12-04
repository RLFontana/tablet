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

    public double getValorTotal() {
        double valorTotal = 0d;
        for(ItemPedido itemPedido : itensPedidos){
            valorTotal += itemPedido.getValorTotal();
        }
        return valorTotal;
    }

    public void addItemPedido(Produto produto){
        ItemPedido itemPedido = createItemPedido(produto);
        int index = itensPedidos.size();
        if (itensPedidos.contains(itemPedido)){
            index = itensPedidos.indexOf(itemPedido);
            itensPedidos.get(index).setQuantidade(itensPedidos.get(index).getQuantidade() + 1);

        }else{
            itensPedidos.add(itemPedido);
        }
        if (index != 0) {
            ItemPedido swap = itensPedidos.set(0, itensPedidos.get(index));
            itensPedidos.set(index, swap);
        }
    }

    public void removeItemPedido(Produto produto){
        ItemPedido itemPedido = createItemPedido(produto);
        if(itensPedidos.contains(itemPedido)){
            int index = itensPedidos.indexOf(itemPedido);
            if(itensPedidos.get(index).getQuantidade() > 1){
                itensPedidos.get(index).setQuantidade(itensPedidos.get(index).getQuantidade() - 1);
                if(index != itensPedidos.size() -1) {
                    ItemPedido swap = itensPedidos.set(itensPedidos.size() -1, itensPedidos.get(index));
                    itensPedidos.set(index, swap);
                }
            }else{
                itensPedidos.remove(itemPedido);
            }
        }
    }

    public void addComanda(Comanda comanda){
        if (!comandas.contains(comanda)) {
            comandas.add(comanda);
        }
    }

    public void removeComanda(Comanda comanda){
        if (comandas.contains(comanda)){
            comandas.remove(comanda);
        }
    }

    private ItemPedido createItemPedido(Produto produto){
        return new ItemPedido(-1, 1, SituacaoItemPedido.CONFIRMADO_PELO_GARCOM, produto.getPreco(), null, comandas.size(), comandas, null, mesa, null, produto);
    }
}
