package br.com.easygo.cliente.firebase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import br.com.easygo.cliente.model.MainCardTipo;

public class FireBaseData implements Serializable {

    private MainCardTipo type;
    private int value;

    public FireBaseData() {

    }

    public FireBaseData(String type, String value) {
        this(MainCardTipo.valueOf(Integer.valueOf(type)), Integer.valueOf(value));
    }

    public FireBaseData(MainCardTipo type, int value) {
        this.type = type;
        this.value = value;
    }

    public MainCardTipo getType() {
        return type;
    }

    public void setType(MainCardTipo type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static FireBaseData parse(Map<String, String> data) {
        JSONObject json = new JSONObject(data);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json.toString(), FireBaseData.class);
    }
}
