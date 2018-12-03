package br.com.easygo.cliente.webservice;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;



public class PostObjectWS extends PostPadraoVolley<Object> {
    public PostObjectWS(Context context, HashMap<String, String> params) {
        super(context, params);
    }

    @Override
    protected Object getObject(JSONObject jsonObject) throws JSONException {
        String a = jsonObject.getString("result");

        return a;
    }


    @Override
    protected String getWebServiceName() {
        return "/teste/postTeste";
    }
}
