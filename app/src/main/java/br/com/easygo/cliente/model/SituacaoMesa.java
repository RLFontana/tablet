package br.com.easygo.cliente.model;

public enum SituacaoMesa {
    DISPONIVEL(1),
    OCUPADA(2),
    INDISPONIVEL(3);

    private int situacao;

    SituacaoMesa(int situacao){
        this.situacao = situacao;
    }

    public int getSituacao(){
        return this.situacao;
    }
}
