package com.pedrokleiz.walletstone.API;

import com.pedrokleiz.walletstone.Model.Bitcoin;
import com.pedrokleiz.walletstone.Model.Brita;
import com.pedrokleiz.walletstone.Model.BritaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoedaService {

    @GET("CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao=''&$top=1&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao")
    Call<BritaResponse> recuperarBrita(@Query("=''") String dataBrita);

   //@GET("CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='04-26-2019'&$top=1&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao")
   @GET("/api/BCH/day-summary/")
    Call<Bitcoin> recuperarBitcoin(@Query("data") String dataBitcoin);

//    GET("")
//    Call<Bitcoin> recuperarBitcoin
}
