package br.com.easygo.cliente.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;
import br.com.easygo.cliente.model.Garcom;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.Pedido;
import br.com.easygo.cliente.model.Produto;
import br.com.easygo.cliente.model.SituacaoMesa;
import br.com.easygo.cliente.model.Solicitacao;
import br.com.easygo.cliente.model.TipoProduto;

/**
 * Created by Vinícius Carvalho on 11/24/2018.
 * Company: OPCAO TI
 * Contact: contato@opcaoti.com
 */
public class InMemoryDB {

    public static void fillObjects(){
        fillMesas();
        fillClientes();
        fillGarcons();
        fillProdutos();
        fillComandas();
        fillSolicitacoes();

        pedidoDAO = new ArrayList<>();
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
    }

    private static void fillSolicitacoes() {
        solitacaoDAO = new ArrayList<>();
        solitacaoDAO.add(new Solicitacao(0, comandaDAO.get(0), garcomDAO.get(0)));
        solitacaoDAO.add(new Solicitacao(1, comandaDAO.get(2), garcomDAO.get(0)));
    }

    private static void fillProdutos() {
        produtoDAO = new ArrayList<>();
        produtoDAO.add(new Produto(0, "Arroz", TipoProduto.ACOMPANHAMENTO, "", 5));
        produtoDAO.add(new Produto(1, "Vinagrete", TipoProduto.ACOMPANHAMENTO, "", 3));
        produtoDAO.add(new Produto(2, "Farofa", TipoProduto.ACOMPANHAMENTO, "", 2));
        produtoDAO.add(new Produto(3, "Glacial 600ml", TipoProduto.CERVEJA, "", 6));
        produtoDAO.add(new Produto(4, "Brahma 600ml", TipoProduto.CERVEJA, "", 7.5));
        produtoDAO.add(new Produto(5, "Heineken 600ml", TipoProduto.CERVEJA, "", 11));
        produtoDAO.add(new Produto(6, "Budweiser 600ml", TipoProduto.CERVEJA, "", 10));
        produtoDAO.add(new Produto(7, "Batata frita", TipoProduto.PORCAO, "", 15));
        produtoDAO.add(new Produto(8, "Calabresa", TipoProduto.PORCAO, "", 20));
        produtoDAO.add(new Produto(9, "Picanha suína", TipoProduto.PORCAO, "", 35));
        produtoDAO.add(new Produto(10, "Contra-filé", TipoProduto.PORCAO, "", 45));
        produtoDAO.add(new Produto(11, "Prato feito", TipoProduto.PRATO, "", 15));
        produtoDAO.add(new Produto(12, "Executivo simples", TipoProduto.PRATO, "", 20));
        produtoDAO.add(new Produto(13, "Executivo gourmet", TipoProduto.PRATO, "", 30));
        produtoDAO.add(new Produto(14, "Sorvete", TipoProduto.SOBREMESA, "", 5));
        produtoDAO.add(new Produto(15, "Picolé", TipoProduto.SOBREMESA, "", 2.5));
        produtoDAO.add(new Produto(16, "Paçoquinha", TipoProduto.SOBREMESA, "", 0.3));
    }

    private static void fillGarcons() {
        garcomDAO = new ArrayList<>();
        garcomDAO.add(new Garcom(0, 1, "Bernardo Almeida"));
    }

    private static void fillClientes() {
        clienteDAO = new ArrayList<>();
        clienteDAO.add(new Cliente(0, "Rafaela","99999-9900", "woman_1" ));
        clienteDAO.add(new Cliente(1, "Guthierry","99999-9901", "man_1" ));
        clienteDAO.add(new Cliente(2, "Vinícius","99999-9902", "man_2" ));
        clienteDAO.add(new Cliente(3, "Luiza","99999-9903", "woman_2" ));
        clienteDAO.add(new Cliente(4, "Denise","99999-9904", "woman_3" ));
        clienteDAO.add(new Cliente(5, "Shayana","99999-9905", "woman_4" ));
        clienteDAO.add(new Cliente(6, "Enzo","99999-9906", "man_3" ));
        clienteDAO.add(new Cliente(7, "Valentina","99999-9907" , "woman_5"));
        clienteDAO.add(new Cliente(8, "Vicente","99999-9908", "man_4" ));
        clienteDAO.add(new Cliente(9, "Liliane","99999-9909", "woman_6" ));
        clienteDAO.add(new Cliente(10, "Jocival","99999-9910", "man_5" ));
        clienteDAO.add(new Cliente(11, "Aroldo","99999-9911", "man_6" ));
    }

    private static void fillMesas() {
        mesaDAO = new ArrayList<>();
        mesaDAO.add(new Mesa(0, 1, 4, SituacaoMesa.DISPONIVEL));
        mesaDAO.add(new Mesa(1, 1, 4, SituacaoMesa.DISPONIVEL));
        mesaDAO.add(new Mesa(2, 2, 4, SituacaoMesa.DISPONIVEL));
        mesaDAO.add(new Mesa(3, 3, 4, SituacaoMesa.DISPONIVEL));
        mesaDAO.add(new Mesa(4, 4, 4, SituacaoMesa.DISPONIVEL));
        mesaDAO.add(new Mesa(5, 5, 4, SituacaoMesa.DISPONIVEL));
    }

    public static List<Solicitacao> solitacaoDAO;
    public static List<Mesa> mesaDAO;
    public static List<Cliente> clienteDAO;
    public static List<Comanda> comandaDAO;
    public static List<Garcom> garcomDAO;
    public static List<Produto> produtoDAO;
    public static List<Pedido> pedidoDAO;
    public static List<ItemPedido> itemPedidoDAO;

}
