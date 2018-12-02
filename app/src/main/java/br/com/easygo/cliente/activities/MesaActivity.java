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
import br.com.easygo.cliente.adapters.MesaAdapter;
import br.com.easygo.cliente.adapters.objects.MesaAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.BundlePedidos;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.PrePedido;

public class MesaActivity extends AppCompatActivity {

    private BundlePedidos prePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        BundlePedidos pedidos = (BundlePedidos) intent.getSerializableExtra("prePedidos");
        if (prePedidos == null){
            prePedidos = new BundlePedidos(InMemoryDB.currentGarcom);
        }
        if (pedidos != null){
            prePedidos = pedidos;
        }

        ArrayList<MesaAdapterObject> mesasArray = new ArrayList<>();
        for(Mesa mesa : InMemoryDB.mesaDAO){
            mesasArray.add(new MesaAdapterObject(mesa));
        }

        MesaAdapter.OnItemClickListener onClick = new MesaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MesaAdapterObject item) {
                if (prePedidos.isReverse()){
                    prePedidos.getPrePedidos().get(prePedidos.getPrePedidos().size() - 1).setMesa(item.getMesa());
                } else {
                    PrePedido prePedido = new PrePedido();
                    prePedido.setMesa(item.getMesa());
                    prePedidos.setPrePedido(prePedido);
                }
                prePedidos.setNormal();
                Intent clienteIntent = new Intent(MesaActivity.this, ClienteActivity.class);
                clienteIntent.putExtra("prePedidos", prePedidos);
                startActivity(clienteIntent);
                finish();
            }
        };

        RecyclerView recyclerView = findViewById(R.id.rv_mesas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        MesaAdapter adapter = new MesaAdapter(this, mesasArray, onClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (!prePedidos.isNew()) {
            if (prePedidos.isReverse()) {
                prePedidos.setNormal();
                Intent clienteIntent = new Intent(MesaActivity.this, ClienteActivity.class);
                clienteIntent.putExtra("prePedidos", prePedidos);
                startActivity(clienteIntent);
            } else if (prePedidos.getPrePedidos().size() != 0) {
                Intent produtoIntent = new Intent(MesaActivity.this, ProdutoActivity.class);
                produtoIntent.putExtra("prePedidos", prePedidos);
                startActivity(produtoIntent);
            }
        }
        finish();
    }
}
