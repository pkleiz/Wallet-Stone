package com.pedrokleiz.walletstone.Auxiliar;

public class MoedasFormatadas {

    DataAtualFormatada data = new DataAtualFormatada();

    public String britaFormatada(){
        data.dataHojeBrita();
        return "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='" +
            data + "'&$top=100&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao;";
    }


    public String bitcoinFormadata(){
        data.dataHojeBitcoin();
        return "https://www.mercadobitcoin.net/api/BTC/day-summary/" +
                 data;
    }
}
