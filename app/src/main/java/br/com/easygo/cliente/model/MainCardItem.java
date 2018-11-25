package br.com.easygo.cliente.model;

import android.view.View;

import java.util.List;

public class MainCardItem {

    private String titulo;
    private String cliente;
    private String status;
    private MainCardTipo tipo;
    private List<MainCardAcao> acoes;

    public MainCardItem(String titulo, String cliente, String status, MainCardTipo tipo, List<MainCardAcao> acoes) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.status = status;
        this.tipo = tipo;
        this.acoes = acoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MainCardAcao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<MainCardAcao> acoes) {
        this.acoes = acoes;
    }

    public MainCardTipo getTipo() {
        return tipo;
    }

    public void setTipo(MainCardTipo tipo) {
        this.tipo = tipo;
    }
}
