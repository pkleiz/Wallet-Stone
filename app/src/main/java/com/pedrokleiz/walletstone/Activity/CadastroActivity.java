package com.pedrokleiz.walletstone.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.Model.User;
import com.pedrokleiz.walletstone.R;

public class CadastroActivity extends AppCompatActivity {
    //region Vars

    private ImageView cadastro_back;
    private EditText cadastro_login, cadastro_password, cadastro_confirmPassword;
    private Button cadastro_submit;
    private FirebaseAuth cadastro_autentication;
    private User user_usuario;

    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //region Recuperar Ids

        cadastro_back = findViewById(R.id.cadastro_back);
        cadastro_login = findViewById(R.id.cadastro_login);
        cadastro_password = findViewById(R.id.cadastro_password);
        cadastro_confirmPassword = findViewById(R.id.cadastro_confirmPassword);
        cadastro_submit = findViewById(R.id.cadastro_submit);

        //endregion

        //region Clicks

        //Volta para tela de Login para tela de login
        cadastro_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Botao Submit
        cadastro_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadastro_login_texto = cadastro_login.getText().toString();
                String cadastro_password_texto = cadastro_password.getText().toString();
                String cadastro_confirmPassword_texto = cadastro_confirmPassword.getText().toString();
                int cadastro_password_size = cadastro_password_texto.length();
                int cadastro_confirmPassword_size = cadastro_confirmPassword_texto.length();

                //verifica se nenhum campo está vazio
                if (((stringIsEmply(cadastro_login_texto) == false) || (stringIsEmply(cadastro_password_texto) == false) || (stringIsEmply(cadastro_confirmPassword_texto) == false))) {
                    notification(v, "Preencha todos os campos");
                } else {
                    if (isValidEmail(cadastro_login_texto) == false) {
                        notification(v, "Preencha um email válido");
                    } else {
                        if ((cadastro_password_size < 6) || (cadastro_confirmPassword_size < 6)) {
                            notification(v, "A senha precisa ter 6 ou mais caracteres");
                        } else {
                            if (cadastro_password_texto.equals(cadastro_confirmPassword_texto) == false) {
                                // valid = isValidEmail(cadastro_login_texto);
                                notification(v, "As senhas não coincidem, tente novamente");
                            } else {
                                if(verificaConexao() == false)
                                {
                                    notification(v, "Sem Internet");
                                } else {
                                    //Grava os dados no Firebase
                                    user_usuario = new User();
                                    user_usuario.setEmail(cadastro_login_texto);
                                    user_usuario.setPassword(cadastro_password_texto);
                                    registerUser();

                                    notification(v, "Cadastro com sucesso!");
                                }
                            }
                        }
                    }
                }
            }
        });

        //endregion
    }

    //region Auxiliar Methods

    //Esse método Testa se o campo não está vazio e retorna false se sim
    private boolean stringIsEmply(String s) {
        if (s.isEmpty()) {

            return false;

        } else {
            return true;
        }
    }

    //Esse metodo cria um SnackBar para notificar com uma msg personaliza
    private void notification(View v, String s) {
        Snackbar.make(v, s, Snackbar.LENGTH_SHORT).show();
    }

    //Metodo Usado para adicionar o usuario no firebase
    private void registerUser() {
        cadastro_autentication = FirebaseConfig.getFirebaseAutentication();
        cadastro_autentication.createUserWithEmailAndPassword(
                user_usuario.getEmail(), user_usuario.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG);
                    //notification(getWindow().getDecorView().findViewById(R.id.content),"Sucesso ao cadastrar");
                } else {
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG);
                    //notification(getWindow().getDecorView().findViewById(R.id.content),"Erro ao cadastrar");
                }
            }
        });
    }

    //Verifica se o Email é valido retornando um true se sim, false senão
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    //Função para verificar existência de conexão com a internet
    public boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    //endregion
}
