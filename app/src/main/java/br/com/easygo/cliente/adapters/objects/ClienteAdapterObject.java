package br.com.easygo.cliente.adapters.objects;

import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;

public class ClienteAdapterObject {
    private Comanda comanda;
    private boolean selected;

    public ClienteAdapterObject(Comanda comanda) {
        this.comanda = comanda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Cliente getCliente(){
        return comanda.getCliente();
    }
}
