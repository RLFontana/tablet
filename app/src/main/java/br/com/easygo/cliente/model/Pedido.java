package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Pedido {

    private int codigo;
    private int numero;
    private Date dataInclusao;
    private Date dataConfirmacao;
    private Garcom garcom;
    private Solicitacao solicitacao;
    private List<Comanda> comandas;
    private List<ItemPedido> listaItemPedido;

    public Pedido(int codigo, int numero, Date dataInclusao, Date dataConfirmacao, Garcom garcom, List<Comanda> comandas, List<ItemPedido> listaItemPedido) {
        this.codigo = codigo;
        this.numero = numero;
        this.dataInclusao = dataInclusao;
        this.dataConfirmacao = dataConfirmacao;
        this.garcom = garcom;
        this.listaItemPedido = listaItemPedido;
    }


    public Pedido(int codigo, int numero, Garcom garcom) {
        this.codigo = codigo;
        this.numero = numero;
        this.dataInclusao = Calendar.getInstance().getTime();
        this.garcom = garcom;
    }

    public Pedido(Garcom garcom, List<Comanda> comandas, ItemPedido pedido){
        this(0, 0, new Date(System.currentTimeMillis()), null, garcom, comandas, new ArrayList<ItemPedido>());
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

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
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
                //comanda.toString() + "\n" +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for(ItemPedido itemPedido : listaItemPedido){
            stringBuilder.append(itemPedido.toString());
        }
        return stringBuilder.toString();
    }
}
