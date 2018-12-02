package br.com.easygo.cliente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.activities.MesaActivity;
import br.com.easygo.cliente.adapters.MainAdapter;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.dialogs.SolicitacaoDialog;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.MainCardAcao;
import br.com.easygo.cliente.model.MainCardItem;
import br.com.easygo.cliente.model.MainCardTipo;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.SituacaoItemPedido;
import br.com.easygo.cliente.model.Solicitacao;

public class MainActivity extends AppCompatActivity {

    List<MainCardItem> itemPedidos;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        InMemoryDB.fillObjects();

        FloatingActionButton fabPedido = findViewById(R.id.fab_novo_pedido);


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    Bundle extra = new Bundle();

                    SolicitacaoDialog solicitacaoDialog = new SolicitacaoDialog();
                    extra.putSerializable("FirebaseData", intent.getSerializableExtra("Data"));
                    solicitacaoDialog.setArguments(extra);
                    solicitacaoDialog.show(getSupportFragmentManager(), "tag");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("notification-firebase"));

        InMemoryDB.currentGarcom = InMemoryDB.garcomDAO.get(0);

        final Context context = this;
        fabPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mesa = new Intent(MainActivity.this, MesaActivity.class);
                context.startActivity(mesa);
            }
        });

        fillList();

        RecyclerView recyclerView = findViewById(R.id.rcvw_cards);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new MainAdapter(this, itemPedidos);
        recyclerView.setAdapter(adapter);
        FirebaseMessaging.getInstance().subscribeToTopic("tablet");
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDataSet();
    }

    public void refreshDataSet(){
        fillList();
        adapter.setItens(itemPedidos);
        adapter.notifyDataSetChanged();
    }



    private void fillList() {
        itemPedidos = new ArrayList<>();

        //SOLICITACAO DE ATENDIMENTO

        for (Solicitacao solicitacao : InMemoryDB.currentGarcom.getListaSolicitacoes()){
            Cliente c = solicitacao.getComanda().getCliente();
            Mesa m = solicitacao.getMesa();

            List<MainCardAcao> acoesSolicitacao = new ArrayList<>();
            acoesSolicitacao.add(new MainCardAcao(0, "Atender", atenderSolicitacao_onClick(solicitacao)));
            acoesSolicitacao.add(new MainCardAcao(1, "Cancelar", cancelarSolicitacao_onClick(solicitacao)));

            itemPedidos.add(new MainCardItem(
                    "Mesa "+ m.getNumero(),
                    c.getNome(),
                    "Solicitando atendimento",
                    MainCardTipo.SOLICITACAO_ATENDIMENTO,
                    acoesSolicitacao
            ));
        }

        //FIM SOLICITACAO DE ATENDIMENTO

        //PEDIDO DO CLIENTE
        //FIM PEDIDO DO CLIENTE



        for (ItemPedido itemPedido : InMemoryDB.currentGarcom.getListaItensPedidos()){

            //ITENS A ENTREGAR
            if (itemPedido.getSituacao() == SituacaoItemPedido.PRONTO_PARA_ENTREGA){

                List<Comanda> comandas = itemPedido.getListaComandas();
                String nomeCliente = "";
                for(Comanda comanda : comandas){
                    nomeCliente += comanda.getCliente().getNome();
                }


                List<MainCardAcao> acoesEntrega = new ArrayList<>();
                acoesEntrega.add(new MainCardAcao(0, "Entregar", entregarItem_onClick(itemPedido)));
                acoesEntrega.add(new MainCardAcao(1, "Cancelar", cancelarEntregaItem_onClick(itemPedido)));
                itemPedidos.add(new MainCardItem(
                        "Mesa "+ itemPedido.getMesa().getNumero(),
                        nomeCliente,
                        itemPedido.getProduto().getNome(),
                        MainCardTipo.ITEM_PEDIDO_ENTREGA,
                        acoesEntrega
                ));
            }
            //FIM ITENS A ENTREGAR


            //ITENS PEDIDOS PELO GARCOM ATUAL
            if (itemPedido.getSituacao() == SituacaoItemPedido.CONFIRMADO_PELO_GARCOM &&
                    itemPedido.getPedido().getGarcom().getMatricula() == InMemoryDB.currentGarcom.getMatricula()){
                List<Comanda> comandas = itemPedido.getListaComandas();
                String nomeCliente = "";
                for(Comanda comanda : comandas){
                    nomeCliente += comanda.getCliente().getNome();
                }


                String titulo = "";
                switch (itemPedido.getProduto().getTipo()){

                    case PORCAO:
                    case PRATO:
                    case ACOMPANHAMENTO:
                    case SOBREMESA:
                        titulo = "Cozinha";
                        break;
                    case OUTROS:
                    case BEBIDA:
                    case CERVEJA:
                    case VINHO:
                    case DRINK:
                        titulo = "Bar";
                        break;
                }
                List<MainCardAcao> acoesPedido = new ArrayList<>();
                acoesPedido.add(new MainCardAcao(0, "Cancelar pedido", cancelarPedido_onClick(itemPedido)));
                itemPedidos.add(new MainCardItem(
                        titulo,
                        nomeCliente,
                        itemPedido.getProduto().getNome(),
                        MainCardTipo.ITEM_PEDIDO_ENTREGA,
                        acoesPedido
                ));
            }
            //FIM ITENS PEDIDOS PELO GARCOM ATUAL
        }


    }

    private View.OnClickListener cancelarPedido_onClick(final ItemPedido itemPedido) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.cancelarItemPedido(itemPedido);
            }
        };
    }

    private View.OnClickListener cancelarEntregaItem_onClick(final ItemPedido itemPedido) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.cancelarItemPedido(itemPedido);
            }
        };
    }

    private View.OnClickListener entregarItem_onClick(final ItemPedido itemPedido) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemPedido.setSituacao(SituacaoItemPedido.ENTREGUE);
            }
        };
    }


    private View.OnClickListener cancelarSolicitacao_onClick(final Solicitacao solicitacao) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.currentGarcom.getListaSolicitacoes().remove(solicitacao);
                //Notificar usuario?
            }
        };
    }

    private View.OnClickListener atenderSolicitacao_onClick(final Solicitacao solicitacao) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.atenderSolicitacao(solicitacao);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
