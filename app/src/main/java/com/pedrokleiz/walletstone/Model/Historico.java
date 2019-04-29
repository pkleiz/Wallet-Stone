package com.pedrokleiz.walletstone.Model;

import java.io.Serializable;
import java.util.Date;

public class Historico implements Serializable {
    private long id;
    private String transacao;
    private Float valorFonte;
    private Float valorDestino;
    private float dinheiroFonte;
    private float dinheiroDestino;
    private Date data;
    private String moedaFonte;
    private String moedaDestino;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public float getValorFonte() {
        return valorFonte;
    }

    public void setValorFonte(float valorFonte) {
        this.valorFonte = valorFonte;
    }

    public float getValorDestino() {
        return valorDestino;
    }

    public void setValorDestino(float valorDestino) {
        this.valorDestino = valorDestino;
    }

    public float getDinheiroFonte() {
        return dinheiroFonte;
    }

    public void setDinheiroFonte(float dinheiroFonte) {
        this.dinheiroFonte = dinheiroFonte;
    }

    public float getDinheiroDestino() {
        return dinheiroDestino;
    }

    public void setDinheiroDestino(float dinheiroDestino) {
        this.dinheiroDestino = dinheiroDestino;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMoedaFonte() {
        return moedaFonte;
    }

    public void setMoedaFonte(String moedaFonte) {
        this.moedaFonte = moedaFonte;
    }

    public String getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(String moedaDestino) {
        this.moedaDestino = moedaDestino;
    }
}
