package br.com.easygo.cliente.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public abstract class GetPadraoVolley<T> extends PadraoVolley<T> {

    public GetPadraoVolley(Context context, HashMap<String, String> params) {
        super(context, params);
    }
    public void execute(){

        try {

            onPreExecuteListener.onPreExecute();

            gravaLog();

            StringRequest sr = new StringRequest(Request.Method.GET, getUrl(), responseListener(), errorListener()) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("MAC", "teste");
                    return params;
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

    protected abstract T getObject(String jsonString) throws JSONException;

    private Response.Listener<String> responseListener(){
        return new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {

                    if (response.contains(ServerExceptions.CODIGO_DE_ERRO)){
                        onErrorListener.onError();
                    }
                    else if (objIsNotNull(response)) {
                        Log.i("VolleyWebService", "Tamanho do arquivo JSON: "+   getSize(response));
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
