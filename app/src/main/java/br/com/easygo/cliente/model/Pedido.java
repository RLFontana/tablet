package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Pedido implements Comparable<Pedido>{

    private int id;
    private int codigo;
    private Date dataInclusao;
    private Date dataConfirmacao;
    private Garcom garcom;
    private List<ItemPedido> listaItemPedido;

    public Pedido(int id, int codigo, Date dataInclusao, Date dataConfirmacao, Garcom garcom, List<ItemPedido> listaItemPedido) {
        this.id = id;
        this.codigo = codigo;
        this.dataInclusao = dataInclusao;
        this.dataConfirmacao = dataConfirmacao;
        this.garcom = garcom;
        this.listaItemPedido = listaItemPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
                "id: " + id + "\n" +
                "dataInclusao: " + Format.date(dataInclusao) + "\n" +
                "dataConfirmacao: " + Format.date(dataConfirmacao) + "\n" +
                garcom.toString() + "\n" +
                //comanda.toString() + "\n" +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for(ItemPedido itemPedido : listaItemPedido){
            stringBuilder.append(itemPedido.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Pedido o) {
        if (this.codigo == o.codigo) {
            return 0;
        } else if (this.codigo > o.codigo) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Pedido o = (Pedido) obj;
        return this.id == o.id && this.codigo == o.codigo && ((this.dataInclusao == null && o.dataInclusao == null) || this.dataInclusao.equals(o.dataInclusao)) && ((this.dataConfirmacao == null && o.dataConfirmacao == null) || this.dataConfirmacao.equals(o.dataConfirmacao)) && this.garcom.equals(o.garcom) && this.listaItemPedido.equals(o.listaItemPedido);
    }
}
