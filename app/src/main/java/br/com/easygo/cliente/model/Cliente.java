package br.com.easygo.cliente.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.util.Format;

public class Cliente {

    private int codigo;
    private String nome;
    private long telefone;
    private Date dataNascimento;
    private String foto;
    private List<Comanda> listaComanda;

    public Cliente(int codigo, String nome, long telefone, Date dataNascimento, String foto, List<Comanda> listaComanda) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
        this.listaComanda = listaComanda;
    }

    public Cliente(String nome, long telefone){
        this(0, nome, telefone, null, "", new ArrayList<Comanda>());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Comanda> getListaComanda() {
        return listaComanda;
    }

    public void setListaComanda(List<Comanda> listaComanda) {
        this.listaComanda = listaComanda;
    }

    @Override
    public String toString() {
        String retorno = "[Cliente]" + "\n" +
                "codigo: " + codigo + "\n" +
                "nome: " + nome + "\n" +
                "telefone: " + Format.phoneNumber(telefone) + "\n" +
                "dataNascimento" + Format.date(dataNascimento) + "\n" +
                "foto: " + foto + "\n" +
                "[Lista]" + "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(retorno);
        for (Comanda comanda : listaComanda){
            stringBuilder.append(comanda.toString());
        }
        return stringBuilder.toString();
    }
}
