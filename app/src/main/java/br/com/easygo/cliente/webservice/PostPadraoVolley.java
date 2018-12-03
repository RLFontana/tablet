package br.com.easygo.cliente.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vinícius Carvalho on 04/02/2018.
 * Company: OPCAO TI
 * Contact: contato@opcaoti.com
 */

public abstract class PostPadraoVolley<T> extends PadraoVolley<T> {


    public PostPadraoVolley(Context context, HashMap<String, String> params) {
        super(context, params);
    }

    public void post(JSONObject jsonObject){

        try {

            onPreExecuteListener.onPreExecute();

            gravaLog();

            JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, getUrl(), jsonObject, responsePostListener(), errorListener()) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    return super.getParams();
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Header", "headerValue");
                    return headers;
                }
            };
            sr.setRetryPolicy(new DefaultRetryPolicy( 20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            sr.setTag(this.getClass().getName());

            this.requestQueue.add(sr);


        }
        catch (Exception ex){
            onErrorListener.onError();
        }

    }


    protected abstract T getObject(JSONObject jsonObject) throws JSONException;

    private Response.Listener<JSONObject> responsePostListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.toString().contains(ServerExceptions.CODIGO_DE_ERRO)){
                        onErrorListener.onError();
                    }
                    else if (objIsNotNull(response.toString())) {
                        Log.i("VolleyWebService", "Tamanho do arquivo JSON: "+   getSize(response.toString()));
                        onPostExecuteListener.onPostExecute(getObject(response));
                    }
                    else
                        onPostExecuteListener.onPostExecute(null);

                } catch (Exception ex){
                    onErrorListener.onError();
                }
            }
        };
    }



}
