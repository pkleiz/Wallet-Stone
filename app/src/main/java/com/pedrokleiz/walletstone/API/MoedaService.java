package com.pedrokleiz.walletstone.API;

import com.pedrokleiz.walletstone.Model.Bitcoin;
import com.pedrokleiz.walletstone.Model.Brita;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MoedaService {

    @GET("CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='{data}'&$top=1&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao")
    Call<Bitcoin> recuperarBrita(@Path("data")String data);

   //@GET("CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='04-26-2019'&$top=1&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao")
   @GET("/api/BCH/day-summary/{data}")
    Call<Brita> recuperarBitcoin(@Path("data")String data);

//    GET("")
//    Call<Bitcoin> recuperarBitcoin
}
