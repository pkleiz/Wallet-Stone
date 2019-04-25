package com.pedrokleiz.walletstone.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 2;
    public static String NOME_DB = "DB_WALLETSTONE";
    public static String TABELA_WALLETSTONE = "walletStone";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_WALLETSTONE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " email TEXT NOT NULL, " +
                " saldo REAL NOT NULL); ";

        //testa se foi tudo ok ao criar a tabela
        try {
            db.execSQL(sql);
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
