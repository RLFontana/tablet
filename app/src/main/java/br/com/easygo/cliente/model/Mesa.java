package br.com.easygo.cliente.model;

public class Mesa {

    private int codigo;
    private int numero;
    private int quantidadeCadeira;
    private SituacaoMesa situacao;

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
}
