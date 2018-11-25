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
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ClienteAdapter;
import br.com.easygo.cliente.adapters.MesaAdapter;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.adapters.objects.MesaAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.SituacaoMesa;

public class MesaActivity extends AppCompatActivity {

    private List<SelectMesa> mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_mesa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        ArrayList<MesaAdapterObject> mesasArray = new ArrayList<>();
        for(Mesa mesa : InMemoryDB.mesaDAO){
            mesasArray.add(new MesaAdapterObject(mesa));
        }

        MesaAdapter.OnItemClickListener onClick = new MesaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MesaAdapterObject item) {
                Intent it = new Intent(MesaActivity.this, ClienteActivity.class);
                it.putExtra("MESA_ID", item.getMesa().getCodigo());
                startActivity(it);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.rv_mesas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        MesaAdapter adapter = new MesaAdapter(this, mesasArray, onClick);
        recyclerView.setAdapter(adapter);
    }


    public class SelectMesa{
        private Mesa mesa;
        private boolean selection;

        public Mesa getMesa() {
            return mesa;
        }

        public void setMesa(Mesa mesa) {
            this.mesa = mesa;
        }

        public boolean isSelection() {
            return selection;
        }

        public void setSelection(boolean selection) {
            this.selection = selection;
        }
    }

}
