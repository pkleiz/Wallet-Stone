package com.pedrokleiz.walletstone.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pedrokleiz.walletstone.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;


    public UsuarioDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("email",usuario.getEmail());
        cv.put("saldo",usuario.getSaldo());
        cv.put("brita",usuario.getBrita());
        cv.put("saldo",usuario.getBitcoin());

        try {
            escreve.insert(DbHelper.TABELA_USUARIO,null,cv);
            Log.e("Info","Sucesso ao salvar Dados");
        }catch (Exception e){
            Log.e("Info","Erro ao salvar Email" + e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean deletar(Usuario usuario) {
        return false;
    }

    @Override
    public List<Usuario> listar(String string) {

        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = le.rawQuery("SELECT email FROM "+DbHelper.TABELA_USUARIO +" WHERE email = "+string+";",null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){

            Usuario usuario = new Usuario();
            String email = cursor.getString(cursor.getColumnIndex("email"));

            usuario.setEmail(email);

            //Log.i("oi",cursor.getString(i));
            cursor.moveToNext();
            usuarios.add(usuario);
        }
        return usuarios;
    }

}
