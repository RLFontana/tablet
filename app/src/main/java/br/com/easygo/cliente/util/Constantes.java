package br.com.easygo.cliente.util;

import java.util.HashMap;
import java.util.Map;

public class Constantes {

    public enum Url {
        CLIENTE(""),
        GARCOM(""),
        COMANDA(""),
        PRODUTO(""),
        PEDIDO(""),
        ITEM_PEDIDO(""),
        MESA("");

        String uri;

        Url(String uri){
            this.uri = uri;
        }

        public String getUri(){
            return this.uri;
        }
    }

    public final static Map<String, String> defaultHeader() {
        Map<String, String> retorno =  new HashMap<String, String>();
        //retorno.put("key", "value");
        return retorno;
    }

    public enum TipoCozinha{
        COZINHA(0),
        BAR(1);

        int codigo;

        TipoCozinha(int codigo){
            this.codigo = codigo;
        }

        public int getCodigo(){
            return this.codigo;
        }

        public static TipoCozinha valueOf(int codigo){
            switch (codigo){
                case 0:
                    return COZINHA;
                case 1:
                default:
                    return BAR;
            }
        }
    }
}
