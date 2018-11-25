package br.com.easygo.cliente.adapters.objects;

import br.com.easygo.cliente.model.Mesa;

public class MesaAdapterObject {
    private Mesa mesa;
    private boolean selected;

    public MesaAdapterObject(Mesa mesa) {
        this.mesa = mesa;
        this.selected = false;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
