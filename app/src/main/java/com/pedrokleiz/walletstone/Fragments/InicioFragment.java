package com.pedrokleiz.walletstone.Fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.pedrokleiz.walletstone.Activity.MainActivity;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.Helper.DbHelper;
import com.pedrokleiz.walletstone.Helper.IUsuarioDAO;
import com.pedrokleiz.walletstone.Helper.UsuarioDAO;
import com.pedrokleiz.walletstone.Model.Usuario;
import com.pedrokleiz.walletstone.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    //region Vars
    private Button inicio_logout;
    private TextView inicio_valorDisponivel_id, inicio_valorAtualBitcoin_id,
            inicio_valorAtualBrita_id, inicio_bitcoinComprado_id,
            inicio_britaComprado_id, inicio_bitcoinRS_id,
            inicio_britaRS_id, inicio_totalComCompras_id;
    SQLiteDatabase sqLiteDatabase;

    String a;
    private FirebaseAuth autenticacao;
    Intent intent = new Intent();
    //endRegion


    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Banco de dados local
        DbHelper db = new DbHelper(getContext());
        //db.getWritableDatabase().insert()
        //colocaDados();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        UsuarioDAO usuarioDAO = new UsuarioDAO(getActivity());

        inicio_valorDisponivel_id = view.findViewById(R.id.inicio_valorDisponivel);
        inicio_valorAtualBitcoin_id = view.findViewById(R.id.inicio_valorAtualBitcoin);
        inicio_valorAtualBrita_id = view.findViewById(R.id.inicio_valorAtualBrita);
        inicio_bitcoinComprado_id = view.findViewById(R.id.inicio_bitcoinComprado);
        inicio_britaComprado_id = view.findViewById(R.id.inicio_britaComprado);
        inicio_bitcoinRS_id = view.findViewById(R.id.inicio_bitcoinRS);
        inicio_britaRS_id = view.findViewById(R.id.inicio_britaRS);
        inicio_totalComCompras_id = view.findViewById(R.id.inicio_totalComCompras);

        db.getWritableDatabase();

        //logout
        inicio_logout = view.findViewById(R.id.inicio_logout);
        inicio_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
            }
        });
        autenticacao = FirebaseConfig.getFirebaseAutentication();
        return view;
    }

    //recupera a instancia se o usuario está logado
    private void verificaLogado() {
        autenticacao.getCurrentUser();
    }

    //recupera o email do usuario logado
    private String recuperaEmail() {
        return autenticacao.getCurrentUser().getEmail().toString();

    }

    //Desloga usuário
    private void deslogarUsuario() {
        try {
            autenticacao.signOut();
            startActivity(new Intent(getActivity(), MainActivity.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Verifica e coloca o dinheiro no BD se a pessoa não tiver
}