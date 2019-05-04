package com.pedrokleiz.walletstone.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.pedrokleiz.walletstone.API.MoedaService;
import com.pedrokleiz.walletstone.Auxiliar.DataAtualFormatada;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.Helper.DbHelper;
import com.pedrokleiz.walletstone.Model.Bitcoin;
import com.pedrokleiz.walletstone.Model.BritaResponse;
import com.pedrokleiz.walletstone.Model.User;
import com.pedrokleiz.walletstone.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //region Vars

    private EditText login_login, login_password;
    private Button login_submit,login_createAccount;
    private User usuario;
    private FirebaseAuth login_autentication,login_verifyIsLogged;
    private String brita, bitcoin;
    private Retrofit retrofitBitcoin, retrofitBrita;
    private DataAtualFormatada dataHoje = new DataAtualFormatada();
    private List<BritaResponse> britaResponses = new ArrayList<>();


    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyUserLogged();

        //region Recuperar Ids

        login_login = findViewById(R.id.login_id_login);
        login_password = findViewById(R.id.login_id_password);
        login_submit = findViewById(R.id.login_id_submit);
        login_createAccount = findViewById(R.id.login_id_createAccount);



        //endregion

        //region

        //botao login
        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String login_login_texto = login_login.getText().toString();
               String login_password_texto = login_password.getText().toString();
                if ((CadastroActivity.stringIsEmply(login_login_texto) == false) || (CadastroActivity.stringIsEmply(login_password_texto) == false)) {
                    CadastroActivity.notification(v, "Preencha todos os campos");
                }else {
                    if (CadastroActivity.isValidEmail(login_login_texto) == false) {
                        CadastroActivity.notification(v, "Preencha um email válido");
                    } else{
                        usuario = new User();
                        usuario.setEmail(login_login_texto);
                        usuario.setPassword(login_password_texto);
                        validateLogin();

                    }
                }
            }
        });

        //botão cadastrar
        login_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });


        //brita = data.britaFormatada();
        //bitcoin = data.bitcoinFormadata();

        retrofitBitcoin = new Retrofit.Builder()
                .baseUrl("https://www.mercadobitcoin.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarMoedasRetrofit();

        retrofitBrita = new Retrofit.Builder()
                .baseUrl("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/")
                //.baseUrl("https://www.mercadobitcoin.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarMoedasRetrofit();


        //instanciando o banco de dados

    }


    //region Auxiliar Methods

    public void recuperarMoedasRetrofit() {
        final String dataBrita = dataHoje.dataHojeBrita();
        String dataBitcoin = dataHoje.dataHojeBitcoin();
        DbHelper db = new DbHelper( getApplicationContext());
        final ContentValues cv = new  ContentValues();


        MoedaService moedasService = retrofitBitcoin.create(MoedaService.class);
        final DataAtualFormatada data = new DataAtualFormatada();

        Call<BritaResponse> call = moedasService.recuperarBrita("10-05-1991");
        Call<Bitcoin> call1 = moedasService.recuperarBitcoin(dataBitcoin);




        call.enqueue(new Callback<BritaResponse>() {
            @Override
            public void onResponse(Call<BritaResponse> call, Response<BritaResponse> response) {
                if(response.isSuccessful()){
                    britaResponses = (List<BritaResponse>) response.body();
                    cv.put("data", dataBrita);
                    cv.put("valorBrita", String.valueOf(britaResponses.get(0))); //Dados da Brita
                }
            }

            @Override
            public void onFailure(Call<BritaResponse> call, Throwable t) {
                Log.i(t.toString(),"falhou");
            }
        });

        call1.enqueue(new Callback<Bitcoin>() {
            @Override
            public void onResponse(Call<Bitcoin> call, Response<Bitcoin> response) {
                if (response.isSuccessful()) {
                    Bitcoin bitcoin = response.body();
                    if (bitcoin.getAvg_price() != null) {
                        if (!(cv.containsKey("2019/04/26"))) {
                            cv.put("data", "2019/04/26");
                            cv.put("valorBitcoin", bitcoin.getAvg_price()); //Dados do bitcoin

                        }
                        else {
                            Log.i("banco", "não funcionou");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Bitcoin> call, Throwable t) {

            }
        });

        db.getWritableDatabase().insert("walletCotacoes",null,cv);
        db.close();
    }


    @Override
    protected void onStart() {
        super.onStart();
        verifyUserLogged();
    }

    static void changeValueOfFieldText(EditText t, String s){
        t.setText(s);
    }

    public void validateLogin() {
        login_autentication = FirebaseConfig.getFirebaseAutentication();
        login_autentication.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    openScreen();
                    finish();
                }else {
                    String excesao;
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        excesao = "Usuário não cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excesao = "Email ou senha errados, tente novamente";
                    } catch (Exception e) {
                        excesao = "Erro no login";
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,excesao,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Vai pra tela de logado
    private void openScreen(){
        startActivity(new Intent(this,LogadoActivity.class));
        finish();
    }

    public void verifyUserLogged(){
        login_verifyIsLogged = FirebaseConfig.getFirebaseAutentication();
        if(login_verifyIsLogged.getCurrentUser() != null){
            openScreen();
        }
    };

    //endregion
}