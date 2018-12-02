package br.com.easygo.cliente.adapters.objects;

import android.os.Build;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Produto;

public class SubPedidoAdapterObject {
    private Mesa mesa;
    private List<Comanda> comandas;
    private List<SubPedidoProduto> produtos;

    public SubPedidoAdapterObject(Mesa mesa, List<Comanda> comandas) {
        this.mesa = mesa;
        this.comandas = comandas;
        this.produtos = new LinkedList<>();
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public List<SubPedidoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<SubPedidoProduto> produtos) {
        this.produtos = produtos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void addProduto(Produto produto, int quantidade){
        boolean novoProduto = true;
        for(SubPedidoProduto subProduto : produtos){
            if(subProduto.getProduto().getCodigo() == produto.getCodigo()){
                subProduto.setQuantidade(subProduto.getQuantidade() + quantidade);
                novoProduto = false;
                break;
            }
        }

        if(novoProduto){
            produtos.add(new SubPedidoProduto(produto, quantidade));
        }
        sortProdutos();
    }

    public void removeProduto(Produto produto, int quantidade){
        int idxToRemove = -1;
        int pos = 0;
        for(SubPedidoProduto subProduto : produtos){
            if(subProduto.getProduto().getCodigo() == produto.getCodigo()){
                subProduto.setQuantidade(subProduto.getQuantidade() - quantidade);
                if(subProduto.getQuantidade() <= 0){
                    idxToRemove = pos;
                }
                break;
            }
            pos++;
        }

        if(idxToRemove > -1){
            produtos.remove(idxToRemove);
        }

        sortProdutos();
    }

    private void sortProdutos(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            produtos.sort(new Comparator<SubPedidoProduto>() {
                @Override
                public int compare(SubPedidoProduto subPedidoProduto, SubPedidoProduto t1) {
                    return t1.getDataModificacao().compareTo(subPedidoProduto.getDataModificacao());
                }
            });
        }
    }

    public double getValorTotal(){
        double valorTotal = 0;
        for(SubPedidoProduto subPedido : produtos){
            valorTotal += subPedido.getProduto().getPrecoDouble() * subPedido.getQuantidade();
        }
        return valorTotal;
    }

    public class SubPedidoProduto{
        private Produto produto;
        private int quantidade;
        private Date dataModificacao;

        public SubPedidoProduto(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
            this.dataModificacao = new Date();
        }

        public Produto getProduto() {
            return produto;
        }

        public void setProduto(Produto produto) {
            this.produto = produto;
            this.dataModificacao = new Date();
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
            this.dataModificacao = new Date();
        }

        public Date getDataModificacao() {
            return dataModificacao;
        }

        public void setDataModificacao(Date dataModificacao) {
            this.dataModificacao = dataModificacao;
        }
    }
}
