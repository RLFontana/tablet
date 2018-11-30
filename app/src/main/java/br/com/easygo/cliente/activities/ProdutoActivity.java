package br.com.easygo.cliente.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ProdutoAdapter;
import br.com.easygo.cliente.adapters.ProdutoDetalheAdapter;
import br.com.easygo.cliente.adapters.SubPedidoAdapter;
import br.com.easygo.cliente.adapters.objects.ProdutoAdapterObject;
import br.com.easygo.cliente.adapters.objects.SubPedidoAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    List<SubPedidoAdapterObject> subPedidos = new LinkedList<>();
    SubPedidoAdapter subPedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        final int mesaID = it.getIntExtra("MESA_ID", -1);
        final int[] clienteID = it.getIntArrayExtra("CLIENTE_ID");

        Mesa mesa = null;
        if(mesaID > -1 && !"".equals(mesaID)){
            for(Mesa mesaSearch : InMemoryDB.mesaDAO){
                if(mesaID == mesaSearch.getNumero()){
                    mesa = mesaSearch;
                    break;
                }
            }
        }

        ArrayList<Cliente> clientes = InMemoryDB.getClienteMesa(mesa);

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


        List<Integer> clientesSelecionados = new LinkedList<>();
        for(int cli : clienteID){
            clientesSelecionados.add(cli);
        }
        List<Cliente> clientesPedido = new LinkedList<>();
        for(Cliente cliente : clientes){
            if(clientesSelecionados.contains(cliente.getCodigo())){
                clientesPedido.add(cliente);
            }
        }

        List<Produto> produtos = InMemoryDB.produtoDAO;
        List<ProdutoAdapterObject> listaProduto = new LinkedList<>();
        for(Produto produto : produtos){
            if(listaProduto.size() == 0 || !listaProduto.get(listaProduto.size() - 1).getTipo().equals(produto.getTipo().name())){
                listaProduto.add(new ProdutoAdapterObject(produto.getTipo().name(), new LinkedList<Produto>()));
                listaProduto.get(listaProduto.size() - 1).getProdutos().add(produto);
                continue;
            }
            listaProduto.get(listaProduto.size() - 1).getProdutos().add(produto);
        }

        ProdutoDetalheAdapter.OnItemClickListener onClick = new ProdutoDetalheAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Produto item) {
                subPedidos.get(0).addProduto(item, 1);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        subPedidoAdapter.notifyDataSetChanged();
                    }
                });
            }
        };


        RecyclerView recyclerView = findViewById(R.id.rv_produtos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaProduto, onClick);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setEnabled(false);
        fab.setAlpha(0.1f);


        subPedidos.add(new SubPedidoAdapterObject(mesa, clientesPedido));
        RecyclerView recyclerViewSubpedido = findViewById(R.id.rv_subpedido);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewSubpedido.setLayoutManager(mLayoutManager);
        subPedidoAdapter = new SubPedidoAdapter(this, subPedidos, null);
        recyclerViewSubpedido.setAdapter(subPedidoAdapter);

    }

}
