package com.pedrokleiz.walletstone.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pedrokleiz.walletstone.Activity.HistoricoAdapter;
import com.pedrokleiz.walletstone.Model.Historico;
import com.pedrokleiz.walletstone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoricoAdapter historicoAdapter;
    private List<Historico> listaHistorico = new ArrayList<>();

    public HistoricoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_historico, container, false);

        //Configura RecycleView
        recyclerView = view.findViewById(R.id.historico_recyclerView);


        return view;
    }

    @Override
    public void onStart() {

        //Inicializa o RecyclerView
        //carregarListaHistorico();
        super.onStart();

    }

    //TODO ver o pq da pagina estar crashando
    public void carregarListaHistorico(){

        //Listar Historico
        Historico historico = new Historico();
        historico.setValorFonte(1252);
        listaHistorico.add(historico);

        Historico historico2 = new Historico();
        historico.setValorDestino(1252);
        listaHistorico.add(historico);

        //Configurar Adapter
        historicoAdapter = new HistoricoAdapter(listaHistorico);


        //Configurar Recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(historicoAdapter);
    }
}
