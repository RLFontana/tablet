package br.com.easygo.cliente.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BundlePedidos implements Serializable {

    private List<PrePedido> prePedidos;
    private Garcom garcom;
    private boolean reverse;

    public BundlePedidos(Garcom garcom) {
        this.garcom = garcom;
        this.prePedidos = new ArrayList<>();
        reverse = false;
    }

    public List<PrePedido> getPrePedidos() {
        return prePedidos;
    }

    public void setPrePedidos(List<PrePedido> prePedidos) {
        this.prePedidos = prePedidos;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public void setPrePedido(PrePedido prePedido){
        this.prePedidos.add(prePedido);
    }

    public PrePedido getPrePedido(int index){
        return prePedidos.get(index);
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse() {
        this.reverse = true;
    }

    public void setNormal(){
        this.reverse = false;
    }

    public Mesa getMesaAtual(){
        return prePedidos.get(prePedidos.size() -1).getMesa();
    }

    public void alteraMesaAtual(Mesa mesa){
        prePedidos.get(prePedidos.size() - 1).setMesa(mesa);
    }

    public boolean isNew(){
        return prePedidos == null || prePedidos.isEmpty() || prePedidos.get(0).getComandas() == null || prePedidos.get(0).getComandas().isEmpty() || prePedidos.get(0).getItensPedidos() == null || prePedidos.get(0).getItensPedidos().isEmpty();
    }

    public List<Comanda> getComandasPedidoAtual(){
        return prePedidos.get(prePedidos.size() -1).getComandas();
    }
}
