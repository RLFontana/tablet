package br.com.easygo.cliente.adapters.objects;

import br.com.easygo.cliente.model.Cliente;

public class ClienteAdapterObject {
    private Cliente cliente;
    private boolean selected;

    public ClienteAdapterObject(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
