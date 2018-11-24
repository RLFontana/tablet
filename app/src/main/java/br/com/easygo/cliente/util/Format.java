package br.com.easygo.cliente.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

    public static String date(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  (date != null) ? simpleDateFormat.format(date) : "--/--/---- --:--";
    }

    public static String price(double value){
        return DecimalFormat.getCurrencyInstance().format(value);
    }

    public static String phoneNumber(long phoneNumber){
        String number = String.valueOf(phoneNumber);
        String prefix = number.substring(0,2);
        String first = number.substring(2,number.length() - 4);
        String last = number.substring(number.length() - 4, number.length());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(0");
        stringBuilder.append(prefix);
        stringBuilder.append(") ");
        stringBuilder.append(first);
        stringBuilder.append("-");
        stringBuilder.append(last);
        return stringBuilder.toString();
    }
}
