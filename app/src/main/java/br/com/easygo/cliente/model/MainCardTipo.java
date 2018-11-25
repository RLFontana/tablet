package br.com.easygo.cliente.model;

public enum MainCardTipo {
    SOLICITACAO_ATENDIMENTO(0),
    PEDIDO_CLIENTE(1),
    ITEM_PEDIDO_CONFIRMADO(2),
    ITEM_PEDIDO_ENTREGA(3);

    private int tipo;

    MainCardTipo(int tipo){
        this.tipo = tipo;
    }

    public int getTipo(){
        return this.tipo;
    }


    public static MainCardTipo valueOf(int tipo){
        switch (tipo){
            case 0:
                return SOLICITACAO_ATENDIMENTO;
            case 1:
                return PEDIDO_CLIENTE;
            case 2:
                return ITEM_PEDIDO_CONFIRMADO;
            default:
                return ITEM_PEDIDO_ENTREGA;
        }
    }
}
