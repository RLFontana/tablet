package br.com.easygo.cliente.model;

import android.view.View;


public class MainCardAcao{
    private int order;
    private String nome;
    private View.OnClickListener onClickListener;

    public MainCardAcao(String nome) {
        this.nome = nome;
    }

    public MainCardAcao(int order, String nome) {
        this.order = order;
        this.nome = nome;
    }

    public MainCardAcao(String nome, View.OnClickListener onClickListener) {
        this.nome = nome;
        this.onClickListener = onClickListener;
    }

    public MainCardAcao(int order, String nome, View.OnClickListener onClickListener) {
        this.order = order;
        this.nome = nome;
        this.onClickListener = onClickListener;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}