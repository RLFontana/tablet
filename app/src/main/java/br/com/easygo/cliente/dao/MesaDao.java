package br.com.easygo.cliente.dao;

import android.content.Context;

public class MesaDao {

    private final Context context;
    private final VolleySingleton volleySingleton;

    public MesaDao(Context context){
        this.context = context;
        volleySingleton = VolleySingleton.getInstance(context);
    }
}
