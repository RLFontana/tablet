package br.com.easygo.cliente.adapters.objects;

import java.util.List;

import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Produto;

public class SubPedidoAdapterObject {
    private List<Cliente> clientes;
    private List<SubPedidoProduto> produtos;

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
    }
}
