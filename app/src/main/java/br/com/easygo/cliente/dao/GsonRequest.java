package br.com.easygo.cliente.dao;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> tClass;
    private final Map<String, String> headers;
    private final Listener<T> listener;
    private final Object object;

    public GsonRequest(int method, String url, Class<T> tClass, Map<String, String> headers, Object object, Listener<T> listener, @Nullable ErrorListener errorListener) {
        super(method, url, errorListener);
        this.tClass = tClass;
        this.headers = headers;
        this.listener = listener;
        this.object = object;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, tClass), HttpHeaderParser.parseCacheHeaders(response));
        }catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return  Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return gson.toJson(object).getBytes();
    }
}
