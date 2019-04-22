package com.pedrokleiz.walletstone.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.Model.User;
import com.pedrokleiz.walletstone.R;

public class MainActivity extends AppCompatActivity {

    //region Vars

    private EditText login_login, login_password;
    private Button login_submit,login_createAccount;
    private User usuario;
    private FirebaseAuth login_autentication,login_verifyIsLogged;

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

    }

    //region Auxiliar Methods


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