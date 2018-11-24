package br.com.easygo.cliente.model;

public enum SituacaoItemPedido {
    INCLUIDO_PELO_CLIENTE(0),
    CONFIRMADO_PELO_GARCOM(1),
    PRONTO_PARA_ENTREGA(2),
    ENTREGA_A_CAMINHO(3),
    ENTREGUE(4),
    CANCELADO(5);

    int situacao;

    SituacaoItemPedido(int situacao){
        this.situacao = situacao;
    }

    public int getSituacao(){
        return this.situacao;
    }
}
