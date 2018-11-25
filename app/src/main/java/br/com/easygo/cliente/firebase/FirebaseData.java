package br.com.easygo.cliente.firebase;

import br.com.easygo.cliente.model.MainCardTipo;

public class FirebaseData {

    private MainCardTipo type;
    private int value;

    public FirebaseData() {

    }

    public FirebaseData(String type, String value) {
        this(MainCardTipo.valueOf(Integer.valueOf(type)), Integer.valueOf(value));
    }

    public FirebaseData(MainCardTipo type, int value) {
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
}
