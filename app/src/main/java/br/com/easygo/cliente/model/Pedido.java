package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Pedido {

    private int codigo;
    private int numero;
    private Date dataInclusao;
    private Date dataConfirmacao;
    private Garcom garcom;
    private Comanda comanda;
    private List<ItemPedido> listaItemPedido;

    public Pedido(int codigo, int numero, Date dataInclusao, Date dataConfirmacao, Garcom garcom, Comanda comanda, List<ItemPedido> listaItemPedido) {
        this.codigo = codigo;
        this.numero = numero;
        this.dataInclusao = dataInclusao;
        this.dataConfirmacao = dataConfirmacao;
        this.garcom = garcom;
        this.comanda = comanda;
        this.listaItemPedido = listaItemPedido;
    }

    public Pedido(Garcom garcom, Comanda comanda, ItemPedido pedido){
        this(0, 0, new Date(System.currentTimeMillis()), null, garcom, comanda, new ArrayList<ItemPedido>());
        this.listaItemPedido.add(pedido);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    @Override
    public String toString() {
        String retorno = "[Pedido]" + "\n" +
                "codigo: " + codigo + "\n" +
                "numero: " + numero + "\n" +
                "dataInclusao: " + Format.date(dataInclusao) + "\n" +
                "dataConfirmacao: " + Format.date(dataConfirmacao) + "\n" +
                garcom.toString() + "\n" +
                comanda.toString() + "\n" +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for(ItemPedido itemPedido : listaItemPedido){
            stringBuilder.append(itemPedido.toString());
        }
        return stringBuilder.toString();
    }
}
