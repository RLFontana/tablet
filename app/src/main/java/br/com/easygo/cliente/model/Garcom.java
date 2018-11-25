package br.com.easygo.cliente.model;

import java.util.List;

public class Garcom {

    private int codigo;
    private int matricula;
    private String nome;
    private List<Solicitacao> solicitacoes;

    public Garcom(){
        this(0, 0, "");
    }

    public Garcom(int codigo, int matricula, String nome) {
        this.codigo = codigo;
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    @Override
    public String toString() {
        return "[Garcom]" + "\n" +
                "codigo: " + codigo + "\n" +
                "matricula: " + codigo + "\n" +
                "nome: " + nome + "\n";
    }
}
