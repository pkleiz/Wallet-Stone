package com.pedrokleiz.walletstone.API;

import android.os.AsyncTask;

import com.pedrokleiz.walletstone.Helper.DbHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskApi extends AsyncTask<String,Void,String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    //retorna uma string
    protected String doInBackground(String... strings) {
        String stringURL = strings[0];
        InputStream inputStream = null;

        try {

            URL url = new URL (stringURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            inputStream = conexao.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream.toString();
    }

    @Override
    //aqui a string é passada
    protected void onPostExecute(String resultado) {
        super.onPostExecute(resultado);

    }
}
