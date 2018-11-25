package br.com.easygo.cliente;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.adapters.PedidoAdapter;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.dialogs.SolicitacaoDialog;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.Garcom;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.MainCardAcao;
import br.com.easygo.cliente.model.MainCardItem;
import br.com.easygo.cliente.model.MainCardTipo;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Pedido;
import br.com.easygo.cliente.model.Produto;
import br.com.easygo.cliente.model.SituacaoItemPedido;
import br.com.easygo.cliente.model.Solicitacao;
import br.com.easygo.cliente.model.TipoProduto;

public class MainActivity extends AppCompatActivity {

    ArrayList<MainCardItem> itemPedidos;
    ListView listaItens;
    private PedidoAdapter adapter;
    private Garcom currentGarcom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InMemoryDB.fillObjects();
        Bundle extra = new Bundle();
        extra.putBoolean("isSolicitacao", false);
        SolicitacaoDialog solicitacaoDialog = new SolicitacaoDialog();
        solicitacaoDialog.setArguments(savedInstanceState);
        solicitacaoDialog.show(getSupportFragmentManager(), "tag");
        currentGarcom = InMemoryDB.garcomDAO.get(0);

        listaItens = findViewById(R.id.lv_pedidos);

        fillList();

        adapter = new PedidoAdapter(getApplicationContext(), itemPedidos);

        listaItens.setAdapter(adapter);
    }

    private void fillList() {
        itemPedidos = new ArrayList<>();

        //SOLICITACAO DE ATENDIMENTO
        List<MainCardAcao> acoesSolicitacao = new ArrayList<>();
        acoesSolicitacao.add(new MainCardAcao(0, "Atender", atenderSolicitacao_onClick()));
        acoesSolicitacao.add(new MainCardAcao(1, "Cancelar", cancelarSolicitacao_onClick()));

        for (Solicitacao solicitacao: InMemoryDB.solitacaoDAO) {
            if(solicitacao.getGarcom().getCodigo() == currentGarcom.getCodigo()
                    && solicitacao.isAtendida()){
                Cliente c = solicitacao.getComanda().getCliente();
                Mesa m =  solicitacao.getComanda().getMesaAtual();
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

        List<MainCardAcao> acoesEntrega = new ArrayList<>();
        acoesEntrega.add(new MainCardAcao(0, "Entregar", entregarItem_onClick()));
        acoesEntrega.add(new MainCardAcao(1, "Cancelar", cancelarEntregaItem_onClick()));


        List<MainCardAcao> acoesPedido = new ArrayList<>();
        acoesPedido.add(new MainCardAcao(0, "Cancelar pedido", entregarItem_onClick()));

        for (ItemPedido itemPedido : InMemoryDB.itemPedidoDAO){

            //ITENS A ENTREGAR
            if (itemPedido.getSituacao() == SituacaoItemPedido.PRONTO_PARA_ENTREGA &&
                    itemPedido.getGarcom().getCodigo() == currentGarcom.getCodigo()){

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
                itemPedidos.add(new MainCardItem(
                        "Bar",
                        nomeCliente,
                        itemPedido.getProduto().getNome(),
                        MainCardTipo.ITEM_PEDIDO_ENTREGA,
                        acoesEntrega
                ));
            }
            //FIM ITENS A ENTREGAR


            //ITENS PEDIDOS PELO GARCOM ATUAL
            if (itemPedido.getSituacao() == SituacaoItemPedido.CONFIRMADO_PELO_GARCOM &&
                    itemPedido.getPedido().getGarcom().getCodigo() == currentGarcom.getCodigo()){
                List<Comanda> comandas = itemPedido.getPedido().getComandas();
                String nomeCliente = "";
                for(Comanda comanda : comandas){
                    nomeCliente += comanda.getCliente().getNome();
                }
                itemPedidos.add(new MainCardItem(
                        "Mesa "+ itemPedido.getMesa().getCodigo(),
                        nomeCliente,
                        itemPedido.getProduto().getNome(),
                        MainCardTipo.ITEM_PEDIDO_ENTREGA,
                        acoesPedido
                ));
            }
            //FIM ITENS PEDIDOS PELO GARCOM ATUAL
        }


    }

    private View.OnClickListener cancelarEntregaItem_onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    private View.OnClickListener entregarItem_onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    private View.OnClickListener cancelarSolicitacao_onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    private View.OnClickListener atenderSolicitacao_onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

}
