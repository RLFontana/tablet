package br.com.easygo.cliente.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;

import br.com.easygo.cliente.model.Cliente;

public class ClienteDao {

    private final Context context;
    private final VolleySingleton volleySingleton;

    public ClienteDao(Context context){
        this.context = context;
        volleySingleton = VolleySingleton.getInstance(context);
    }

    public void createCliente(Context context, Cliente cliente, Listener listener, ErrorListener errorListener){

    }

    public void readClientes(Context context, Listener listener, ErrorListener errorListener){

    }

    public void updateCliente(Context context, Cliente cliente, Listener listener, ErrorListener errorListener){

    }

    public void removeCliente(Cliente cliente){

    }

    private void addRequest(Request request){

    }
}
