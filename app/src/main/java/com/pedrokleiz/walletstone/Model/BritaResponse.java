package com.pedrokleiz.walletstone.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BritaResponse {
        @SerializedName("value")
        private List<Brita> value;

    public List<Brita> getValue() {
        return value;
    }

    public void setValue(List<Brita> value) {
        this.value = value;
    }
}
