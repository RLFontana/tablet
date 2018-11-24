package br.com.easygo.cliente.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import br.com.easygo.cliente.model.Garcom;

public class GarcomDao {

    private final Context context;
    private final VolleySingleton volleySingleton;

    public GarcomDao(Context context){
        this.context = context;
        volleySingleton = VolleySingleton.getInstance(context);
    }

    public void createComanda(Context context, Garcom garcom, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void readComandas(Context context, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void updateComanda(Context context, Garcom garcom, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void removeComanda(Garcom garcom){

    }

    private void addRequest(Request request){

    }
}
