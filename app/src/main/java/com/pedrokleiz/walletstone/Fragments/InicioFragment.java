package com.pedrokleiz.walletstone.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.pedrokleiz.walletstone.Activity.MainActivity;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    private Button inicio_logout;
    private FirebaseAuth autenticacao;
    Intent intent = new Intent();

    //configuracoes de objetos

    public InicioFragment() {
        // Required empty public constructor
    }

    //region Vars



    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        inicio_logout = view.findViewById(R.id.inicio_logout);
        autenticacao = FirebaseConfig.getFirebaseAutentication();

        inicio_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
            }
        });

        return view;
    }

    //recupera a instancia se o usuario está logado
    private void verificaLogado(){
        autenticacao.getCurrentUser();
    }

    //Desloga usuário
    private void deslogarUsuario(){
        try{
            autenticacao.signOut();
            startActivity(new Intent(getActivity(), MainActivity.class));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
