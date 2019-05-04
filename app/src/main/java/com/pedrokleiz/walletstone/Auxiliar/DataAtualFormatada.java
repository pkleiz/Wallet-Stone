package com.pedrokleiz.walletstone.Auxiliar;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAtualFormatada {

    public String dataHojeBrita(){

        SimpleDateFormat sdff = new SimpleDateFormat("MM-dd-yyyy");
        String dia = (sdff.format(new Date()));
        return dia;
    }

    public String dataHojeBitcoin(){

        SimpleDateFormat sdff = new SimpleDateFormat("yyyy/MM/dd");
        String dia = (sdff.format(new Date()));
        return dia;
    }
}
