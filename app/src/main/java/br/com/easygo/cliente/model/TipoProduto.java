package br.com.easygo.cliente.model;

public enum TipoProduto {
    PORCAO(1),
    PRATO(2),
    ACOMPANHAMENTO(3),
    SOBREMESA(4),
    BEBIDA(5),
    CERVEJA(6),
    VINHO(7),
    DRINK(8),
    OUTROS(9);

    private int tipo;

    TipoProduto(int tipo){
        this.tipo = tipo;
    }

    public int getTipo(){
        return this.tipo;
    }

}
