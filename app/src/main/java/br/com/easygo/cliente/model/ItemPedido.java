package br.com.easygo.cliente.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class ItemPedido implements Comparable<ItemPedido> {

    private int id;
    private int quantidade;
    private SituacaoItemPedido situacao;
    private BigDecimal precoUnitario;
    private Date dataEntrega;
    private Garcom garcom;
    private Mesa mesa;
    private Pedido pedido;
    private Produto produto;
    private List<Comanda> listaComandas;

    public ItemPedido(int id, int quantidade, SituacaoItemPedido situacao, BigDecimal precoUnitario, Date dataEntrega, int qtComandas, List<Comanda> listaComandas, Garcom garcom, Mesa mesa, Pedido pedido, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.situacao = situacao;
        this.precoUnitario = precoUnitario;
        this.dataEntrega = dataEntrega;
        this.listaComandas = listaComandas;
        this.garcom = garcom;
        this.mesa = mesa;
        this.pedido = pedido;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public SituacaoItemPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoItemPedido situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getQtComandas() {
        return listaComandas != null ? listaComandas.size() : 0;
    }

    public List<Comanda> getListaComandas() {
        return listaComandas;
    }

    public void setListaComandas(List<Comanda> listaComandas) {
        this.listaComandas = listaComandas;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        String retorno =  "[ItemPedido]" +
                "id: " + id + "\n" +
                "quantidade: " + quantidade + "\n" +
                "situacao: " + situacao + "\n" +
                "precoUnitario: " + precoUnitario + "\n" +
                "dataEntrega: " + Format.date(dataEntrega) + "\n" +
                "garcom: " + garcom.toString() +
                "mesa: " + mesa.toString() +
                "pedido: " + pedido.toString() +
                "produto: " + produto.toString() +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for(Comanda comanda : listaComandas){
            stringBuilder.append(comanda.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(ItemPedido o) {
        if (this.id == o.id) {
            return 0;
        } else if (this.id > o.id) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        ItemPedido o = (ItemPedido) obj;
        return this.id == o.id && this.quantidade == o.quantidade && this.situacao.equals(o.situacao) && this.precoUnitario.equals(o.precoUnitario) && ((this.dataEntrega == null && o.dataEntrega == null) || this.dataEntrega.equals(o.dataEntrega)) &&this.garcom.equals(o.garcom) && this.mesa.equals(o.mesa) && this.pedido.equals(o.pedido) && this.produto.equals(o.produto) && listaComandas.equals(listaComandas);
    }
}
