package com.pedrokleiz.walletstone.Auxiliar;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DataAtualFormatada {

    public String dataHojeBrita(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = new SimpleDateFormat("MM-dd-yyyy").format(timestamp.getTime());
        Log.i(date,"coisa "+date);
        return date;
    }

    public String dataHojeBitcoin(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = new SimpleDateFormat("yyyy/MM/dd").format(timestamp.getTime());
        return date;
    }
}
