package br.com.easygo.cliente.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import br.com.easygo.cliente.model.Comanda;

public class ComandaDao {

    private final Context context;
    private final VolleySingleton volleySingleton;

    public ComandaDao(Context context){
        this.context = context;
        volleySingleton = VolleySingleton.getInstance(context);
    }

    public void createComanda(Context context, Comanda comanda, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void readComandas(Context context, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void updateComanda(Context context, Comanda comanda, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void removeComanda(Comanda comanda){

    }

    private void addRequest(Request request){

    }
}
