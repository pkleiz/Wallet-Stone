package com.pedrokleiz.walletstone.Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrokleiz.walletstone.Model.Historico;
import com.pedrokleiz.walletstone.R;

import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.MyViewHolder> {

    List<Historico> listaHistorico;

    public HistoricoAdapter(List<Historico> historico) {
        this.listaHistorico = historico;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemHistorico = LayoutInflater.from(parent.getContext())
                                                .inflate(R.layout.lista_historico_adapter, parent, false);

        return new MyViewHolder(itemHistorico);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Historico historico = listaHistorico.get(position);
        holder.valorFonte.setText((int) historico.getValorFonte());
        holder.valorDestino.setText((int) historico.getValorDestino());
        holder.dinheiroFonte.setText((int) historico.getDinheiroFonte());
        holder.dinheiroDestino.setText((int) historico.getDinheiroDestino());
        holder.moedaFonte.setText(historico.getMoedaFonte());
        holder.moedaDestino.setText(historico.getMoedaDestino());
    }

    @Override
    public int getItemCount() {
        return listaHistorico.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView valorFonte,valorDestino,dinheiroFonte,dinheiroDestino,transacao,moedaFonte, moedaDestino;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            valorFonte = itemView.findViewById(R.id.historico_valorFonte);
            valorDestino = itemView.findViewById(R.id.historico_valorDestino);
            dinheiroFonte = itemView.findViewById(R.id.historico_dinheiroFonte);
            dinheiroDestino = itemView.findViewById(R.id.historico_dinheiroDestino);

            moedaFonte = itemView.findViewById(R.id.historico_moedaFonte);
            moedaDestino = itemView.findViewById(R.id.historico_moedaDestino);
            transacao = itemView.findViewById(R.id.historico_transacao);
        }
    }
}
