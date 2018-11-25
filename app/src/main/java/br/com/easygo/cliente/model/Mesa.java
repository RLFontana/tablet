package br.com.easygo.cliente.model;

import java.util.List;

public class Mesa {

    private int codigo;
    private int numero;
    private int quantidadeCadeira;
    private SituacaoMesa situacao;
    private List<Comanda> comandas;
    private List<ItemPedido> itensPedidos;

    public Mesa(int codigo, int numero, int quantidadeCadeira, SituacaoMesa situacao) {
        this.codigo = codigo;
        this.numero = numero;
        this.quantidadeCadeira = quantidadeCadeira;
        this.situacao = situacao;
    }



    public Mesa(int numero, int quantidadeCadeira){
        this(0, numero, quantidadeCadeira, SituacaoMesa.DISPONIVEL);
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

    public int getQuantidadeCadeira() {
        return quantidadeCadeira;
    }

    public void setQuantidadeCadeira(int quantidadeCadeira) {
        this.quantidadeCadeira = quantidadeCadeira;
    }

    public SituacaoMesa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoMesa situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "[Mesa]" + "\n" +
                "codigo:" + codigo + "\n" +
                "numero: " + numero + "\n" +
                "quantidadeCadeira: " + quantidadeCadeira + "\n" +
                "situacao: " + situacao + "\n";
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
}
