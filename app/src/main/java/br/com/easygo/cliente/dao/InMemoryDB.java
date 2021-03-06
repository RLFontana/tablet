package br.com.easygo.cliente.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.easygo.cliente.firebase.FireBaseData;
import br.com.easygo.cliente.model.BundlePedidos;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.Garcom;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Pedido;
import br.com.easygo.cliente.model.PrePedido;
import br.com.easygo.cliente.model.Produto;
import br.com.easygo.cliente.model.SituacaoItemPedido;
import br.com.easygo.cliente.model.SituacaoMesa;
import br.com.easygo.cliente.model.Solicitacao;
import br.com.easygo.cliente.model.TipoProduto;
import br.com.easygo.cliente.util.Constantes;

/**
 * Created by Vinícius Carvalho on 11/24/2018.
 * Company: OPCAO TI
 * Contact: contato@opcaoti.com
 */
public class InMemoryDB {

    public static Garcom currentGarcom;

    public static List<Mesa> mesaDAO;
    public static List<Cliente> clienteDAO;
    public static List<Comanda> comandaDAO;
    public static List<Garcom> garcomDAO;
    public static List<Produto> produtoDAO;
    public static List<Pedido> pedidoDAO;
    public static List<ItemPedido> itemPedidoDAO;

    public static void fillObjects(){
        fillMesas();
        fillClientes();
        fillGarcons();
        fillProdutos();
        fillComandas();

        pedidoDAO = new ArrayList<>();
        itemPedidoDAO = new ArrayList<>();
    }

    private static void fillMesas() {
        mesaDAO = new ArrayList<>();
        mesaDAO.add(new Mesa(0, 1, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
        mesaDAO.add(new Mesa(1, 2, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
        mesaDAO.add(new Mesa(2, 3, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
        mesaDAO.add(new Mesa(3, 4, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
        mesaDAO.add(new Mesa(4, 5, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
        mesaDAO.add(new Mesa(5, 6, 4, SituacaoMesa.DISPONIVEL, new ArrayList<ItemPedido>()));
    }

    private static void fillClientes() {
        clienteDAO = new ArrayList<>();
        clienteDAO.add(new Cliente(0, "Rafaela Rodrigues","99999-9900", "woman_1" ));
        clienteDAO.add(new Cliente(1, "Guthierry Gonzaga","99999-9901", "man_1" ));
        clienteDAO.add(new Cliente(2, "Vinícius Vieira","99999-9902", "man_2" ));
        clienteDAO.add(new Cliente(3, "Luiza Lourosa","99999-9903", "woman_2" ));
        clienteDAO.add(new Cliente(4, "Mariza Marcelina","99999-9904", "woman_3" ));
        clienteDAO.add(new Cliente(5, "Shayana Soares","99999-9905", "woman_4" ));
        clienteDAO.add(new Cliente(6, "Enzo Esperandio","99999-9906", "man_3" ));
        clienteDAO.add(new Cliente(7, "Valentina Viegas","99999-9907" , "woman_5"));
        clienteDAO.add(new Cliente(8, "Vicente Velasquez","99999-9908", "man_4" ));
        clienteDAO.add(new Cliente(9, "Liliane Lavas","99999-9909", "woman_6" ));
        clienteDAO.add(new Cliente(10, "Jocival Júnior","99999-9910", "man_5" ));
        clienteDAO.add(new Cliente(11, "Aroldo Almeida","99999-9911", "man_6" ));
        clienteDAO.add(new Cliente(12, "Denise Franzotti","99999-9912", "denise" ));
        clienteDAO.add(new Cliente(13, "Daniel Oliveira","99999-9913", "daniel" ));
        clienteDAO.add(new Cliente(14, "Eliana Caus","99999-9914", "eliana" ));
    }

    private static void fillComandas() {
        comandaDAO = new ArrayList<>();
        comandaDAO.add(new Comanda(0, 1,  clienteDAO.get(0)));
        comandaDAO.add(new Comanda(1, 2,  clienteDAO.get(1)));
        comandaDAO.add(new Comanda(2, 3,  clienteDAO.get(2)));
        comandaDAO.add(new Comanda(3, 4,  clienteDAO.get(3)));
        comandaDAO.add(new Comanda(4, 5,  clienteDAO.get(4)));
        comandaDAO.add(new Comanda(5, 6,  clienteDAO.get(5)));
        comandaDAO.add(new Comanda(6, 7,  clienteDAO.get(6)));
        comandaDAO.add(new Comanda(7, 8,  clienteDAO.get(7)));
        comandaDAO.add(new Comanda(8, 9,  clienteDAO.get(8)));
        comandaDAO.add(new Comanda(9, 10,  clienteDAO.get(9)));
        comandaDAO.add(new Comanda(10, 11,  clienteDAO.get(10)));
        comandaDAO.add(new Comanda(11, 12,  clienteDAO.get(11)));
        comandaDAO.add(new Comanda(12, 13,  clienteDAO.get(12)));
        comandaDAO.add(new Comanda(13, 14,  clienteDAO.get(13)));
        comandaDAO.add(new Comanda(14, 15,  clienteDAO.get(14)));
    }

    private static void fillProdutos() {
        produtoDAO = new ArrayList<>();
        produtoDAO.add(new Produto(0, 1,"Arroz", TipoProduto.ACOMPANHAMENTO, "", 5d));
        produtoDAO.add(new Produto(1, 2,"Vinagrete", TipoProduto.ACOMPANHAMENTO, "", 3d));
        produtoDAO.add(new Produto(2, 3,"Farofa", TipoProduto.ACOMPANHAMENTO, "", 2d));
        produtoDAO.add(new Produto(3, 4,"Glacial 600ml", TipoProduto.CERVEJA, "", 6d));
        produtoDAO.add(new Produto(4, 5,"Brahma 600ml", TipoProduto.CERVEJA, "", 7.5d));
        produtoDAO.add(new Produto(5, 6,"Heineken 600ml", TipoProduto.CERVEJA, "", 11d));
        produtoDAO.add(new Produto(6, 7,"Budweiser 600ml", TipoProduto.CERVEJA, "", 10d));
        produtoDAO.add(new Produto(7, 8,"Batata frita", TipoProduto.PORCAO, "", 15d));
        produtoDAO.add(new Produto(8, 9,"Calabresa", TipoProduto.PORCAO, "", 20d));
        produtoDAO.add(new Produto(9, 10,"Picanha suína", TipoProduto.PORCAO, "", 35d));
        produtoDAO.add(new Produto(10, 11,"Contra-filé", TipoProduto.PORCAO, "", 45d));
        produtoDAO.add(new Produto(11, 12,"Prato feito", TipoProduto.PRATO, "", 15d));
        produtoDAO.add(new Produto(12, 13,"Executivo simples", TipoProduto.PRATO, "", 20d));
        produtoDAO.add(new Produto(13, 14,"Executivo gourmet", TipoProduto.PRATO, "", 30d));
        produtoDAO.add(new Produto(14, 15,"Sorvete", TipoProduto.SOBREMESA, "", 5d));
        produtoDAO.add(new Produto(15, 16,"Picolé", TipoProduto.SOBREMESA, "", 2.5d));
        produtoDAO.add(new Produto(16, 17,"Paçoquinha", TipoProduto.SOBREMESA, "", 0.3d));
        produtoDAO.add(new Produto(17, 18,"Dose Bananinha", TipoProduto.DRINK, "", 5.0d));
    }

    private static void fillGarcons() {
        garcomDAO = new ArrayList<>();
        garcomDAO.add(new Garcom(0, "1", "Bernardo Bernardes", new ArrayList<ItemPedido>(), new ArrayList<Pedido>(), new ArrayList<Solicitacao>()));
    }

    public static void atenderSolicitacao(Solicitacao solicitacao){
        currentGarcom.getListaSolicitacoes().add(solicitacao);
    }

    public static void atenderPedido(Pedido pedido){
        pedido.setGarcom(currentGarcom);
        pedido.setDataConfirmacao(new Date(System.currentTimeMillis()));
        pedidoDAO.add(pedido);
        currentGarcom.getListaPedidos().add(pedido);
        for(ItemPedido itemPedido : pedido.getListaItemPedido()){
            itemPedidoDAO.add(itemPedido);
            currentGarcom.getListaItensPedidos().add(itemPedido);
        }
    }

    public static void confirmarPedido(Pedido pedido){
        for (ItemPedido itemPedido : pedido.getListaItemPedido()){
            itemPedido.setSituacao(SituacaoItemPedido.CONFIRMADO_PELO_GARCOM);
        }
    }

    public static void cancelarPedido(Pedido pedido){
        for (ItemPedido itemPedido : pedido.getListaItemPedido()){
            itemPedido.setSituacao(SituacaoItemPedido.CANCELADO);
        }
    }

    public static void cancelarItemPedido(ItemPedido itemPedido){
        itemPedido.setSituacao(SituacaoItemPedido.CANCELADO);
    }

    public static void entregarItemPedido(ItemPedido itemPedido){
        itemPedido.setSituacao(SituacaoItemPedido.ENTREGA_A_CAMINHO);
    }

    public static ArrayList<Comanda> getComandaMesa(Mesa mesa){
        ArrayList<Comanda> retorno = new ArrayList<>();
        for(ItemPedido itemPedido : itemPedidoDAO){
            if(itemPedido.getMesa().equals(mesa)){
                for(Comanda comanda : itemPedido.getListaComandas()){
                    retorno.add(comanda);
                }
            }
        }
        if(retorno.size() == 0){
            return (ArrayList<Comanda>) comandaDAO;
        }
        return retorno;
    }

    private static int getIndexNextPedido(){
        return pedidoDAO.size();
    }

    private static int getIndexNextItemPedido(){
        return pedidoDAO.size() > 0 ? pedidoDAO.get(pedidoDAO.size() - 1).getListaItemPedido().get(pedidoDAO.get(pedidoDAO.size() - 1).getListaItemPedido().size() - 1).getId() + 1 : 0;
    }

    public static Mesa getMesa(FireBaseData firebaseData){
        switch (firebaseData.getType()){
            case SOLICITACAO_ATENDIMENTO:
                int idMesa = firebaseData.getOrigin();
                for (Mesa mesa : mesaDAO){
                    if (mesa.getId() == idMesa){
                        return mesa;
                    }
                }
                break;
            case ITEM_PEDIDO_ENTREGA:
                int idItemPedido = firebaseData.getValue();
                for (ItemPedido itemPedido : itemPedidoDAO){
                    if(itemPedido.getId() == idItemPedido){
                        return itemPedido.getMesa();
                    }
                }
                break;
        }
        return null;
    }

    public static Cliente getCliente(FireBaseData fireBaseData){
        switch (fireBaseData.getType()){
            case SOLICITACAO_ATENDIMENTO:
                int idCliente = fireBaseData.getValue();
                for (Cliente cliente :clienteDAO){
                    if(cliente.getId() == idCliente){
                        return cliente;
                    }
                }
                break;
            case ITEM_PEDIDO_ENTREGA:
                int idItemPedido = fireBaseData.getValue();
                for (ItemPedido itemPedido : itemPedidoDAO){
                    if (itemPedido.getId() == idItemPedido){
                        return itemPedido.getListaComandas().get(0).getCliente();
                    }
                }
                break;
        }
        return null;
    }

    public static ItemPedido getItemPedido(FireBaseData fireBaseData){
        for (ItemPedido itemPedido : itemPedidoDAO){
            if (itemPedido.getId() == fireBaseData.getValue()){
                return itemPedido;
            }
        }
        return null;
    }

    public static String getNomeOrigem(FireBaseData fireBaseData){
        return Constantes.TipoCozinha.valueOf(fireBaseData.getOrigin()).name();
    }

    public static void createPedido(BundlePedidos prePedidos) {
        int numeroPedido = pedidoDAO.size();
        final Date date = new Date(System.currentTimeMillis());
        Pedido pedido = new Pedido(numeroPedido -1, numeroPedido, prePedidos.getDataCriacao() == null ? date : prePedidos.getDataCriacao(), date, currentGarcom, new ArrayList<ItemPedido>());
        for (PrePedido prePedido : prePedidos.getPrePedidos()){
            for(ItemPedido itemPedido : prePedido.getItensPedidos()){
                int indexItemPedido = itemPedidoDAO.size();
                itemPedido.setId(indexItemPedido -1);
                itemPedido.setListaComandas(prePedido.getComandas());
                itemPedido.setPedido(pedido);
                for(Comanda comanda : prePedido.getComandas()){
                    comanda.getListaItemPedido().add(itemPedido);
                }
                itemPedidoDAO.add(itemPedido);
                pedido.getListaItemPedido().add(itemPedido);
                currentGarcom.getListaItensPedidos().add(itemPedido);
                itemPedido.getMesa().getItensPedidos().add(itemPedido);
                itemPedido.getPedido().getListaItemPedido().add(itemPedido);
            }
        }
        pedidoDAO.add(pedido);
    }
}
