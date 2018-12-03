package br.com.easygo.cliente.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import br.com.easygo.cliente.webservice.interfaces.IOnCancelListener;
import br.com.easygo.cliente.webservice.interfaces.IOnErrorListener;
import br.com.easygo.cliente.webservice.interfaces.IOnPostExecuteListener;
import br.com.easygo.cliente.webservice.interfaces.IOnPreExecuteListener;

/**
 * Created by VMC on 09/09/2015.
 */
public abstract class PadraoVolley<T>  {

    private static String NOME_APLICATIVO_WS = "/opfast.webapi/";
    private static String SERVER = "192.168.15.3:8088";

    protected RequestQueue requestQueue;
    HashMap<String, String> params;
    protected IOnPreExecuteListener onPreExecuteListener;
    protected IOnPostExecuteListener<T> onPostExecuteListener;
    protected IOnErrorListener onErrorListener;
    protected IOnCancelListener onCancelListener;
    protected Context context;


    public void setOnPostExecuteListener(IOnPostExecuteListener<T> onPostExecuteListener) {
        this.onPostExecuteListener = onPostExecuteListener;
    }

    public void setOnPreExecuteListener(IOnPreExecuteListener onPreExecuteListener) {
        this.onPreExecuteListener = onPreExecuteListener;
    }

    public void setOnErrorListener(IOnErrorListener onErrorListener){
        this.onErrorListener = onErrorListener;
    }

    public void setOnCancelListener(IOnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public PadraoVolley(Context context, HashMap<String, String> params) {

        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);


        this.params = params == null ? new HashMap<String, String>() : params;

        this.onPreExecuteListener = new IOnPreExecuteListener() {
            @Override
            public void onPreExecute() {

            }
        };
        this.onPostExecuteListener = new IOnPostExecuteListener<T>() {
            @Override
            public void onPostExecute(T result) {

            }
        };
        this.onErrorListener = new IOnErrorListener() {
            @Override
            public void onError() {

            }
        };
        this.onCancelListener = new IOnCancelListener() {
            @Override
            public void onCancel() {

            }
        };
    }


    protected abstract String getWebServiceName();


    protected String getUrl(){
        return "http://" + getServer()  + NOME_APLICATIVO_WS + getWebServiceName();
    }

    private String getServer(){
        //return (config.getPorta() == 0) ? config.getServidor() : config.getServidor() + ":" + config.getPorta();
        return SERVER;
    }




    public void cancel(){
        this.requestQueue.cancelAll(this.getClass().getName());
        PadraoVolley.this.onCancelListener.onCancel();
    }


    protected void gravaLog(){
        try {
            String queryString = "?";
            for (Map.Entry<String,String> entry : this.params.entrySet()){
                if(entry.getValue() != null)
                    queryString += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&";
            }
            queryString = queryString.substring(0, queryString.length()-1);

            Log.i("VolleyWebService", "Consultando WebService: " + getUrl() + queryString);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    protected Response.ErrorListener errorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String msgError = "";

                if ((error!=null)&&(error.getMessage()!=null))
                    msgError = error.getMessage();
                else if ((error!=null)&&(error.toString()!=null))
                    msgError = error.toString();
                else if ((error!=null)&&(error.getCause()!=null))
                    msgError = error.getCause().toString();

                String msgCompleta = "Falha ao acessar Web service:" + PadraoVolley.this.getUrl() + " | " + msgError;


                if (error != null && error.networkResponse != null){
                    try {
                        String serverMessage = new String(error.networkResponse.data, "UTF-8");
                        msgCompleta += "\n\n Status Code: "+ String.valueOf(error.networkResponse.statusCode);
                        msgCompleta += "\n\n Message: "+ serverMessage;
                    } catch (UnsupportedEncodingException e) {

                    }
                }
                Log.e("PadraoVolley", msgCompleta);


                //PadraoVolley.this.onPostExecuteListener.onPostExecute(null);
                PadraoVolley.this.onErrorListener.onError();
            }
        };
    }


    protected String getSize(String response){

        byte[] bytes = response.getBytes();

        long total = 0;

        for (byte b : bytes)
            total += b;

        if (total < 1024)
            return total + "b";
        else {
            int exp = (int) (Math.log(total) / Math.log(1024));
            String pre = String.valueOf(("KMGTPE").charAt(exp - 1));
            return String.format("%.1f %sb", total / Math.pow(1024, exp), pre);
        }
    }


    protected boolean objIsNotNull(String obj){
        return  (!obj.equals("")) && (!obj.equals("[]"));
    }



}
