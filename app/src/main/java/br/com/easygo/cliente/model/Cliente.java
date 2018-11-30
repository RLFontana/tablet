package br.com.easygo.cliente.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.util.Format;

public class Cliente implements Comparable<Cliente>{

    private int codigo;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String foto;
    private List<Comanda> listaComanda;

    public Cliente(int codigo, String nome, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Cliente(int codigo, String nome, String telefone, String foto) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.foto = foto;
    }

    public Cliente(int codigo, String nome, String telefone, Date dataNascimento, String foto, List<Comanda> listaComanda) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
        this.listaComanda = listaComanda;
    }

    public Cliente(String nome, String telefone){
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
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

    public int getImageResource() {
        if(this.foto == null || "".equals(this.foto)) return R.drawable.avatar;
        try {
            Field idField = R.drawable.class.getDeclaredField(this.foto);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return R.drawable.avatar;
        }
    }

    @Override
    public String toString() {
        String retorno = "[Cliente]" + "\n" +
                "codigo: " + codigo + "\n" +
                "nome: " + nome + "\n" +
                "telefone: " + telefone + "\n" +
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

    @Override
    public int compareTo(Cliente o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public boolean equals(Object obj) {
        Cliente o = (Cliente) obj;
        return this.codigo == o.codigo && this.nome.equals(o.nome) && this.telefone.equals(o.telefone) && ((this.dataNascimento == null && o.dataNascimento == null) || this.dataNascimento.equals(o.dataNascimento)) && ((this.foto == null && o.foto == null) || this.foto.equals(o.foto)) && this.listaComanda.equals(o.listaComanda);

    }
}
