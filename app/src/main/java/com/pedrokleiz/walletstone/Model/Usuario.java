package com.pedrokleiz.walletstone.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    String email;
    Float saldo;
    int bitcoin;
    int brita;

    public int getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(int bitcoin) {
        this.bitcoin = bitcoin;
    }

    public int getBrita() {
        return brita;
    }

    public void setBrita(int brita) {
        this.brita = brita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
