package br.com.easygo.cliente.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ClienteAdapter;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Mesa;

public class ClienteActivity extends AppCompatActivity {

    ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent it = getIntent();
        final int mesaID = it.getIntExtra("MESA_ID", -1);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_cliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ClienteActivity.this, ProdutoActivity.class);
                it.putExtra("MESA_ID", mesaID);
                it.putExtra("CLIENTE_ID", adapter.getSelecionados());
                startActivity(it);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        Mesa mesa = null;
        if(mesaID > -1 && !"".equals(mesaID)){
            for(Mesa mesaSearch : InMemoryDB.mesaDAO){
                if(mesaID == mesaSearch.getCodigo()){
                    mesa = mesaSearch;
                    break;
                }
            }
        }

        TextView textMesa = findViewById(R.id.txt_mesa_cliente);
        textMesa.setText(String.valueOf(mesa.getNumero())) ;

        ArrayList<ClienteAdapterObject> clientesArray = new ArrayList<>();
        List<Cliente> clientes = mesa.getClientes();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            clientes.sort(new Comparator<Cliente>() {
                @Override
                public int compare(Cliente cliente, Cliente t1) {
                    String clienteName1 = cliente.getNome().toUpperCase();
                    String clienteName2 = t1.getNome().toUpperCase();
                    return clienteName1.compareTo(clienteName2);
                }
            });
        }

        for(Cliente cliente : clientes){
            clientesArray.add(new ClienteAdapterObject(cliente));
        }

        ClienteAdapter.OnItemClickListener onClick = new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClienteAdapterObject item, final List<Integer> selectedItems) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(selectedItems != null && selectedItems.size() > 0){
                                fab.setVisibility(View.VISIBLE);
                            }else{
                                fab.setVisibility(View.GONE);
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

}
