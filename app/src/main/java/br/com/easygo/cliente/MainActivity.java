package br.com.easygo.cliente;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.easygo.cliente.adapters.PedidoAdapter;
import br.com.easygo.cliente.dialogs.SolicitacaoDialog;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.Garcom;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Pedido;
import br.com.easygo.cliente.model.Produto;
import br.com.easygo.cliente.model.SituacaoItemPedido;
import br.com.easygo.cliente.model.TipoProduto;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemPedido> itemPedidos;
    ListView listaItens;
    private static PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extra = new Bundle();
        extra.putBoolean("isSolicitacao", false);
        SolicitacaoDialog solicitacaoDialog = new SolicitacaoDialog();
        solicitacaoDialog.setArguments(savedInstanceState);
        solicitacaoDialog.show(getSupportFragmentManager(), "tag");

        listaItens = findViewById(R.id.lv_pedidos);

        itemPedidos = new ArrayList<>();
        mockList(itemPedidos);

        adapter = new PedidoAdapter(getApplicationContext(), itemPedidos);
        listaItens.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void mockList(final ArrayList<ItemPedido> lista){
        Cliente joarez = new Cliente("Joarez", 27981376109l);
        Garcom pedro = new Garcom(1, 1, "Pedro");
        Comanda comanda1 = new Comanda(1, joarez);
        Pedido pedido1 = null;
        ItemPedido itemPedido1 = new ItemPedido(1, new Produto(1, "Batata frita com cheddar e bacon", TipoProduto.PORCAO, "Batatas fritas cobertas com queijo cheddar, salpicadas com bacon crocante em cubinhos", 18.90d), 1, SituacaoItemPedido.CONFIRMADO_PELO_GARCOM, 18.90d, new Mesa(1, 4), pedido1);
        pedido1 = new Pedido(pedro, comanda1, itemPedido1);
        lista.add(itemPedido1);
        lista.add(itemPedido1);
        lista.add(itemPedido1);
        lista.add(itemPedido1);
        lista.add(itemPedido1);
    }
}
