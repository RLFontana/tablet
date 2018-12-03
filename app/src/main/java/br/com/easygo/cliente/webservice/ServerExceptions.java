package br.com.easygo.cliente.webservice;

/**
 * Created by VMC on 02/09/2015.
 */
public class ServerExceptions {

    public static final String CODIGO_DE_ERRO = "Server Error in '/opfast' OPFastApplication";

    private static final String JSON_MAX_LENGHT = "Error during serialization or deserialization using the JSON JavaScriptSerializer. The length of the string exceeds the value set on the maxJsonLength property.";

    public static String getMensagemErro(String json) {

        String erro = json.substring(json.indexOf("<title>")+7, json.indexOf("</title>"));


        switch (erro) {
            case JSON_MAX_LENGHT:
                return "A consulta possui muitos dados. Execute uma consulta menor.";
            default:
                return "";
        }

    }
}
