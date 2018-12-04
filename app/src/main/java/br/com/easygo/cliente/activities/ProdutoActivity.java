package br.com.easygo.cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.ItemSubPedidoAdapter;
import br.com.easygo.cliente.adapters.ProdutoAdapter;
import br.com.easygo.cliente.adapters.ProdutoDetalheAdapter;
import br.com.easygo.cliente.adapters.SubPedidoAdapter;
import br.com.easygo.cliente.adapters.objects.ProdutoAdapterObject;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.model.BundlePedidos;
import br.com.easygo.cliente.model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    SubPedidoAdapter subPedidoAdapter;
    private BundlePedidos prePedidos;
    private LinearLayout btnNovoSubPedido;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        prePedidos = (BundlePedidos) intent.getSerializableExtra("prePedidos");
        fab = findViewById(R.id.fab);

        btnNovoSubPedido = findViewById(R.id.btn_add_subpedido);
        btnNovoSubPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: criar novo prePedido
            }
        });

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
                //apenas o último é editável
                prePedidos.getPrePedidoAtual().addItemPedido(item);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        subPedidoAdapter.notifyDataSetChanged();
                        setFabVisibility();
                        setBtnNovoPedidoVisibility();
                    }
                });
            }
        };

        ItemSubPedidoAdapter.OnItemClickListener onRemoveClick = new ItemSubPedidoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Produto item) {
                //apenas o último é editável
                prePedidos.getPrePedidoAtual().removeItemPedido(item);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        subPedidoAdapter.notifyDataSetChanged();
                        setFabVisibility();
                        setBtnNovoPedidoVisibility();
                    }
                });
            }
        };

        RecyclerView recyclerView = findViewById(R.id.rv_produtos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaProduto, onClick);
        recyclerView.setAdapter(adapter);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.createPedido(prePedidos);
                finish();
            }
        });

        setFabVisibility();

        //subPedidos.add(new SubPedidoAdapterObject(prePedidos.getMesaAtual(), prePedidos.getComandasPedidoAtual()));
        RecyclerView recyclerViewSubpedido = findViewById(R.id.rv_subpedido);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewSubpedido.setLayoutManager(mLayoutManager);
        subPedidoAdapter = new SubPedidoAdapter(this, prePedidos, null, onRemoveClick);
        recyclerViewSubpedido.setAdapter(subPedidoAdapter);

    }

    private void setBtnNovoPedidoVisibility() {
        if(prePedidos.isFilled()){
            btnNovoSubPedido.setVisibility(View.VISIBLE);

        }else{
            btnNovoSubPedido.setVisibility(View.GONE);
        }
    }

    private void setFabVisibility() {
        if(prePedidos.isFilled()){
            fab.setEnabled(true);
            fab.setAlpha(1.0f);
        } else {
            fab.setEnabled(false);
            fab.setAlpha(0.1f);
        }
    }

}
