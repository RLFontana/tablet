package br.com.easygo.cliente.webservice;

import android.content.Context;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by Vinícius Carvalho on 11/12/2017.
 * Company: OPCAO TI
 * Contact: contato@opcaoti.com
 */

public class GetObjectWS extends GetPadraoVolley<String> {
    public GetObjectWS(Context context, HashMap<String, String> params) {
        super(context, params);
    }

    @Override
    protected String getObject(String jsonString) throws JSONException {
        return jsonString;
    }

    @Override
    protected String getWebServiceName() {
        return "/teste/getTeste";
    }
}
