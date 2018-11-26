package br.com.easygo.cliente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.activities.MesaActivity;
import br.com.easygo.cliente.adapters.MainAdapter;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.dialogs.SolicitacaoDialog;
import br.com.easygo.cliente.firebase.FireBaseData;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.MainCardAcao;
import br.com.easygo.cliente.model.MainCardItem;
import br.com.easygo.cliente.model.MainCardTipo;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Pedido;
import br.com.easygo.cliente.model.SituacaoItemPedido;
import br.com.easygo.cliente.model.Solicitacao;

public class MainActivity extends AppCompatActivity {

    List<MainCardItem> itemPedidos;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InMemoryDB.fillObjects();


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    Bundle extra = new Bundle();

                    SolicitacaoDialog solicitacaoDialog = new SolicitacaoDialog();
                    MainCardTipo tipo = ((FireBaseData)intent.getSerializableExtra("Data")).getType();
                    if (tipo == MainCardTipo.SOLICITACAO_ATENDIMENTO) {
                        extra.putBoolean("isSolicitacao", true);
                        int codigoComanda = 1;
                        InMemoryDB.insertSolicitacao(codigoComanda);
                        refreshDataSet();
                    }
                    else if (tipo == MainCardTipo.ITEM_PEDIDO_ENTREGA){
                        extra.putBoolean("isSolicitacao", false);
                        int codigoItemPedido = 1;
                        InMemoryDB.insertItemPedidoPronto(codigoItemPedido);
                        refreshDataSet();
                    }
                    solicitacaoDialog.setArguments(extra);
                    solicitacaoDialog.show(getSupportFragmentManager(), "tag");
                    refreshDataSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("notification-firebase"));

        InMemoryDB.currentGarcom = InMemoryDB.garcomDAO.get(0);

        Button pedido = findViewById(R.id.button);
        final Context context = this;
        pedido.setOnClickListener(new View.OnClickListener() {
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

    public void refreshDataSet(){
        fillList();
        adapter.setItens(itemPedidos);
        adapter.notifyDataSetChanged();
    }



    private void fillList() {
        itemPedidos = new ArrayList<>();

        //SOLICITACAO DE ATENDIMENTO

        for (Solicitacao solicitacao: InMemoryDB.solitacaoDAO) {
            if(solicitacao.getGarcom().getCodigo() == InMemoryDB.currentGarcom.getCodigo()
                    && solicitacao.isAtendida()){
                Cliente c = solicitacao.getComanda().getCliente();
                Mesa m =  solicitacao.getComanda().getMesaAtual();


                List<MainCardAcao> acoesSolicitacao = new ArrayList<>();
                acoesSolicitacao.add(new MainCardAcao(0, "Atender", atenderSolicitacao_onClick(solicitacao)));
                acoesSolicitacao.add(new MainCardAcao(1, "Cancelar", cancelarSolicitacao_onClick(solicitacao)));

                itemPedidos.add(new MainCardItem(
                        "Mesa "+ m.getCodigo(),
                        c.getNome(),
                        "Solicitando atendimento",
                        MainCardTipo.SOLICITACAO_ATENDIMENTO,
                        acoesSolicitacao
                        ));
            }
        }
        //FIM SOLICITACAO DE ATENDIMENTO

        //PEDIDO DO CLIENTE
        //FIM PEDIDO DO CLIENTE



        for (ItemPedido itemPedido : InMemoryDB.itemPedidoDAO){

            //ITENS A ENTREGAR
            if (itemPedido.getSituacao() == SituacaoItemPedido.PRONTO_PARA_ENTREGA &&
                    itemPedido.getGarcom().getCodigo() == InMemoryDB.currentGarcom.getCodigo()){

                List<Comanda> comandas = itemPedido.getPedido().getComandas();
                String nomeCliente = "";
                for(Comanda comanda : comandas){
                    nomeCliente += comanda.getCliente().getNome();
                }


                List<MainCardAcao> acoesEntrega = new ArrayList<>();
                acoesEntrega.add(new MainCardAcao(0, "Entregar", entregarItem_onClick(itemPedido)));
                acoesEntrega.add(new MainCardAcao(1, "Cancelar", cancelarEntregaItem_onClick(itemPedido)));
                itemPedidos.add(new MainCardItem(
                        "Mesa "+ itemPedido.getMesa().getCodigo(),
                        nomeCliente,
                        itemPedido.getProduto().getNome(),
                        MainCardTipo.ITEM_PEDIDO_ENTREGA,
                        acoesEntrega
                ));
            }
            //FIM ITENS A ENTREGAR


            //ITENS PEDIDOS PELO GARCOM ATUAL
            if (itemPedido.getSituacao() == SituacaoItemPedido.CONFIRMADO_PELO_GARCOM &&
                    itemPedido.getPedido().getGarcom().getCodigo() == InMemoryDB.currentGarcom.getCodigo()){
                List<Comanda> comandas = itemPedido.getPedido().getComandas();
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
                int index = InMemoryDB.itemPedidoDAO.indexOf(itemPedido);
                InMemoryDB.itemPedidoDAO.get(index).setSituacao(SituacaoItemPedido.CANCELADO);
            }
        };
    }

    private View.OnClickListener cancelarEntregaItem_onClick(final ItemPedido itemPedido) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = InMemoryDB.itemPedidoDAO.indexOf(itemPedido);
                InMemoryDB.itemPedidoDAO.get(index).setSituacao(SituacaoItemPedido.CANCELADO);
            }
        };
    }

    private View.OnClickListener entregarItem_onClick(final ItemPedido itemPedido) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = InMemoryDB.itemPedidoDAO.indexOf(itemPedido);
                InMemoryDB.itemPedidoDAO.get(index).setSituacao(SituacaoItemPedido.ENTREGUE);
            }
        };
    }

    private View.OnClickListener cancelarSolicitacao_onClick(final Solicitacao solicitacao) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InMemoryDB.solitacaoDAO.remove(solicitacao);
                //Notificar usuario?
            }
        };
    }

    private View.OnClickListener atenderSolicitacao_onClick(final Solicitacao solicitacao) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = InMemoryDB.solitacaoDAO.indexOf(solicitacao);
                InMemoryDB.solitacaoDAO.get(index).setAtendida(true);
                Pedido pedido = new Pedido(0, InMemoryDB.pedidoDAO.size(), InMemoryDB.currentGarcom);
                InMemoryDB.pedidoDAO.add(pedido);
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

}
