package com.pedrokleiz.walletstone.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_WALLETSTONE";
    public static String TABELA_USUARIO = "walletUsuario";
    public static String TABELA_COTACOES = "walletCotacoes";
    public static String TABELA_HISTORICO = "walletHistorico";


    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " email TEXT NOT NULL, " +
                " saldo REAL NOT NULL," +
                " brita REAL NOT NULL," +
                " bitcoin REAL NOT NULL); ";

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABELA_COTACOES
                + " (id INTEGER, " +
                " data DATE PRIMARY KEY NOT NULL, " +
                " valorBitcoin REAL ," +
                " valorBrita REAL ); ";

        String sql3 = "CREATE TABLE IF NOT EXISTS " + TABELA_HISTORICO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " data DATE NOT NULL, " +
                " operacao REAL NOT NULL," +
                " valorBitcoin REAL NOT NULL," +
                " valorBrita REAL NOT NULL); ";



        //testa se foi tudo ok ao criar a tabela
        try {
            db.execSQL(sql);
            db.execSQL(sql2);
            db.execSQL(sql3);
            Log.i("Info DB", "Sucesso ao criar tabela");

        }catch (Exception e)
        {
            Log.i("Info DB", "Erro ao criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
