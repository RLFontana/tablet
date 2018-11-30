package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Comanda implements Comparable<Comanda>{

    private int codigo;
    private int id;
    private Date dataAbertura;
    private Date dataFechamento;
    private Cliente cliente;
    private List<ItemPedido> listaItemPedido;

    public Comanda(int codigo, int id, Date dataAbertura, Date dataFechamento, Cliente cliente, List<ItemPedido> listaItemPedido) {
        this.codigo = codigo;
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.cliente = cliente;
        this.listaItemPedido = listaItemPedido;
    }


    public Comanda(int codigo, int id, Cliente cliente) {
        this.codigo = codigo;
        this.id = id;
        this.dataAbertura = Calendar.getInstance().getTime();
        this.cliente = cliente;
    }

    public Comanda(int id, Cliente cliente){
        this(0, id, new Date(System.currentTimeMillis()), null, cliente, new ArrayList<ItemPedido>());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    @Override
    public String toString() {
        String retorno = "[Comanda]" + "\n"+
                "codigo: " + codigo + "\n" +
                "id: " + id + "\n" +
                "dataAbertura: " + Format.date(dataAbertura) + "\n" +
                "dataFechamento: " + Format.date(dataFechamento) + "\n" +
                cliente.toString() +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for (ItemPedido itemPedido: listaItemPedido){
            stringBuilder.append(itemPedido.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Comanda o) {
        if (this.codigo == o.codigo) {
            return 0;
        } else if(this.codigo > o.codigo) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Comanda o = (Comanda) obj;
        return this.codigo == o.codigo && this.id == o.id && ((this.dataAbertura == null && o.dataAbertura == null) || this.dataAbertura.equals(o.dataAbertura)) && ((this.dataFechamento == null && o.dataFechamento == null) || this.dataFechamento.equals(o.dataFechamento)) && this.cliente.equals(o.cliente) && this.listaItemPedido.equals(o.listaItemPedido);
    }
}
