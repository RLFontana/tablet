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
}