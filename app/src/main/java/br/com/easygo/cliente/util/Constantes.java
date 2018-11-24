package br.com.easygo.cliente.util;

import java.util.HashMap;
import java.util.Map;

public class Constantes {

    public enum Urls {
        CLIENTE(""),
        GARCOM(""),
        COMANDA(""),
        PRODUTO(""),
        PEDIDO(""),
        ITEM_PEDIDO(""),
        MESA("");

        String uri;

        Urls(String uri){
            this.uri = uri;
        }

        public String getUri(){
            return this.uri;
        }
    }

    public enum TipoDialog {
        SOLICITACAO_CLIENTE(true),
        NOTIFICACAO_COZINHA(false);

        boolean solicitacao;

        TipoDialog(boolean solicitacao){
            this.solicitacao = solicitacao;
        }

        public boolean isSolicitacao() {
            return solicitacao;
        }
    }

    public final static Map<String, String> defaultHeader() {
        Map<String, String> retorno =  new HashMap<String, String>();
        //retorno.put("key", "value");
        return retorno;
    }
}
