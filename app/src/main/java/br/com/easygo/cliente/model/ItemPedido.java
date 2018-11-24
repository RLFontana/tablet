package br.com.easygo.cliente.model;

import java.math.BigDecimal;

import br.com.easygo.cliente.util.Format;

public class ItemPedido {
    private int codigo;
    private Produto produto;
    private int quantidade;
    private SituacaoItemPedido situacao;
    private BigDecimal precoUnitario;
    private Garcom garcom;
    private Mesa mesa;
    private Pedido pedido;

    public ItemPedido(int codigo, Produto produto, int quantidade, SituacaoItemPedido situacao, BigDecimal precoUnitario, Garcom garcom, Mesa mesa, Pedido pedido) {
        this.codigo = codigo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.situacao = situacao;
        this.precoUnitario = precoUnitario;
        this.garcom = garcom;
        this.mesa = mesa;
        this.pedido = pedido;
    }

    public ItemPedido(int codigo, Produto produto, int quantidade, SituacaoItemPedido situacao, double precoUnitario, Mesa mesa, Pedido pedido) {
        this(codigo, produto, quantidade, situacao, new BigDecimal(precoUnitario), new Garcom(), mesa, pedido);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public double getPrecoUnitarioDouble(){
        return precoUnitario.doubleValue();
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario){
        this.precoUnitario = new BigDecimal(precoUnitario);
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

    @Override
    public String toString() {
        return "[ItemPedido]" + "\n" +
                "codigo: " + codigo + "\n" +
                "quantidade: " + quantidade + "\n" +
                "situacao: " + situacao + "\n" +
                "precoUnitario: " + Format.price(getPrecoUnitarioDouble()) + "\n" +
                produto.toString() +
                garcom.toString() +
                mesa.toString() +
                pedido != null ? pedido.toString() : "";
    }
}
