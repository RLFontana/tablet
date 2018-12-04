package br.com.easygo.cliente.model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BundlePedidos implements Serializable {

    private List<PrePedido> prePedidos;
    private Garcom garcom;
    private boolean reverse;
    private Date dataCriacao;

    public BundlePedidos(Garcom garcom){
        this(garcom, null);
    }

    public BundlePedidos(Garcom garcom, @Nullable Date dataCriacao) {
        this.garcom = garcom;
        this.dataCriacao = dataCriacao;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Mesa getMesaAtual(){
        return prePedidos.get(getIndexAtual()).getMesa();
    }

    public void alteraMesaAtual(Mesa mesa){
        prePedidos.get(getIndexAtual()).setMesa(mesa);
    }

    public boolean isNew(){
        return prePedidos == null || prePedidos.isEmpty() || prePedidos.get(0).getComandas() == null || prePedidos.get(0).getComandas().isEmpty() || prePedidos.get(0).getItensPedidos() == null || prePedidos.get(0).getItensPedidos().isEmpty();
    }

    public List<Comanda> getComandasPedidoAtual(){
        return prePedidos.get(prePedidos.size() -1).getComandas();
    }

    public PrePedido getPrePedidoAtual(){
        return prePedidos.get(getIndexAtual());
    }

    private int getIndexAtual(){
        return prePedidos.size() -1;
    }

    public void addNewPrepedido(){
        PrePedido newPrePedido = new PrePedido();
        newPrePedido.setMesa(getMesaAtual());
        prePedidos.add(newPrePedido);
    }

    public void removeLastPrePedido(){
        if(prePedidos != null && !prePedidos.isEmpty()){
            prePedidos.remove(getIndexAtual());
        }
    }

    public boolean isFilled(){
        return !getPrePedidoAtual().getItensPedidos().isEmpty();
    }
}
