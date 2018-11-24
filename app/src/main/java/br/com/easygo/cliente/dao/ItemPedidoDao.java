package br.com.easygo.cliente.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import br.com.easygo.cliente.model.ItemPedido;

public class ItemPedidoDao {

    private final Context context;
    private final VolleySingleton volleySingleton;

    public ItemPedidoDao(Context context){
        this.context = context;
        volleySingleton = VolleySingleton.getInstance(context);
    }

    public void createComanda(Context context, ItemPedido itemPedido, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void readComandas(Context context, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void updateComanda(Context context, ItemPedido itemPedido, Response.Listener listener, Response.ErrorListener errorListener){

    }

    public void removeComanda(ItemPedido itemPedido){

    }

    private void addRequest(Request request){

    }
}
