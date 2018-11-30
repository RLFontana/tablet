package br.com.easygo.cliente.firebase;

import java.io.Serializable;

import br.com.easygo.cliente.model.MainCardTipo;

public class FireBaseData implements Serializable {

    private MainCardTipo type;
    private int value;
    private int origin;

    public FireBaseData(String type, String value, String origin) {
        this(MainCardTipo.valueOf(Integer.valueOf(type)), Integer.valueOf(value), Integer.valueOf(origin));
    }

    public FireBaseData(MainCardTipo type, int value, int origin) {
        this.type = type;
        this.value = value;
        this.origin = origin;
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

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }
}
