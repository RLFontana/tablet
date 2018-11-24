package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Comanda {

    private int codigo;
    private int numero;
    private Date dataAbertura;
    private Date dataFechamento;
    private Cliente cliente;
    private List<Pedido> listaPedido;

    public Comanda(int codigo, int numero, Date dataAbertura, Date dataFechamento, Cliente cliente, List<Pedido> listaPedido) {
        this.codigo = codigo;
        this.numero = numero;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.cliente = cliente;
        this.listaPedido = listaPedido;
    }

    public Comanda(int numero, Cliente cliente){
        this(0, numero, new Date(System.currentTimeMillis()), null, cliente, new ArrayList<Pedido>());
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(List<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    @Override
    public String toString() {
        String retorno = "[Comanda]" + "\n"+
                "codigo: " + codigo + "\n" +
                "numero: " + numero + "\n" +
                "dataAbertura: " + Format.date(dataAbertura) + "\n" +
                "dataFechamento: " + Format.date(dataFechamento) + "\n" +
                cliente.toString() +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for(Pedido pedido: listaPedido){
            stringBuilder.append(pedido.toString());
        }
        return stringBuilder.toString();
    }
}
