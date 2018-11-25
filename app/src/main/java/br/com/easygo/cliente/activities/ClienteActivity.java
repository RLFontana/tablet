package br.com.easygo.cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ClienteAdapter;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Mesa;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent it = getIntent();
        String mesaID = it.getStringExtra("MESA_ID");

        Mesa mesa = null;
        if(mesaID != null && !"".equals(mesaID)){
            for(Mesa mesaSearch : InMemoryDB.mesaDAO){
                if(mesaID.equals(mesaSearch.getCodigo())){
                    mesa = mesaSearch;
                    break;
                }
            }
        }

        ArrayList<ClienteAdapterObject> clientesArray = new ArrayList<>();
        for(Cliente cliente : mesa.getClientes()){
            clientesArray.add(new ClienteAdapterObject(cliente));
        }

        clientesArray.add(new ClienteAdapterObject(new Cliente("teste", "teste")));


        ClienteAdapter.OnItemClickListener onClick = new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClienteAdapterObject item) {
            }
        };


        RecyclerView recyclerView = findViewById(R.id.rv_clientes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        ClienteAdapter adapter = new ClienteAdapter(this, clientesArray, onClick);
        recyclerView.setAdapter(adapter);


    }

}
