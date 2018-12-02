package br.com.easygo.cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ClienteAdapter;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.BundlePedidos;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;

public class ClienteActivity extends AppCompatActivity {

    ClienteAdapter adapter;
    private BundlePedidos prePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        prePedidos = (BundlePedidos) intent.getSerializableExtra("prePedidos");


        final FloatingActionButton fab = findViewById(R.id.fab_cliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent produtoIntent = new Intent(ClienteActivity.this, ProdutoActivity.class);
                produtoIntent.putExtra("prePedidos", prePedidos);
                startActivity(produtoIntent);
                finish();

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        fab.setEnabled(false);
        fab.setAlpha(0.1f);

        TextView textMesa = findViewById(R.id.txt_mesa_cliente);
        textMesa.setText(String.valueOf(prePedidos.getMesaAtual().getNumero())) ;

        ArrayList<ClienteAdapterObject> clientesArray = new ArrayList<>();
        ArrayList<Comanda> comandas = InMemoryDB.getComandaMesa(prePedidos.getMesaAtual());

        Collections.sort(comandas);

        for(Comanda comanda : comandas){
            clientesArray.add(new ClienteAdapterObject(comanda));
        }

        ClienteAdapter.OnItemClickListener onClick = new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final ClienteAdapterObject item) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (prePedidos.getComandasPedidoAtual().contains(item.getComanda())) {
                                prePedidos.getComandasPedidoAtual().remove(item.getComanda());
                            } else {
                                prePedidos.getComandasPedidoAtual().add(item.getComanda());
                            }
                            if(prePedidos.getComandasPedidoAtual() != null && !prePedidos.getComandasPedidoAtual().isEmpty()){
                                fab.setEnabled(true);
                                fab.setAlpha(1.0f);
                            }else {
                                fab.setEnabled(false);
                                fab.setAlpha(0.1f);
                            }
                        }
                    });
            }
        };


        RecyclerView recyclerView = findViewById(R.id.rv_clientes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        adapter = new ClienteAdapter(this, clientesArray, onClick);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        prePedidos.setReverse();
        Intent produtoIntent = new Intent(ClienteActivity.this, MesaActivity.class);
        produtoIntent.putExtra("prePedidos", prePedidos);
        startActivity(produtoIntent);
        finish();
    }
}
