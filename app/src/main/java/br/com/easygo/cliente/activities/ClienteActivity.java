package br.com.easygo.cliente.activities;

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
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.model.Cliente;

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

        /*ArrayList<ClienteAdapterObject> clientesArray = new ArrayList<>();
        clientesArray.add(new ClienteAdapterObject(new Cliente()));
        clientesArray.add(new ClienteAdapterObject());

        RecyclerView recyclerView = findViewById(R.id.rv_mesas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        MesaAdapter adapter = new MesaAdapter(this, mesasArray);
        recyclerView.setAdapter(adapter);*/


    }

}
