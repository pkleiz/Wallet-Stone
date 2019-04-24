package com.pedrokleiz.walletstone.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.pedrokleiz.walletstone.Config.FirebaseConfig;
import com.pedrokleiz.walletstone.Fragments.ComprarFragment;
import com.pedrokleiz.walletstone.Fragments.HistoricoFragment;
import com.pedrokleiz.walletstone.Fragments.InicioFragment;
import com.pedrokleiz.walletstone.Fragments.TrocarFragment;
import com.pedrokleiz.walletstone.Fragments.VenderFragment;
import com.pedrokleiz.walletstone.R;

public class LogadoActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private void habilitarNavegacao(BottomNavigationViewEx viewEx) {

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit();
                        return true;
                    case R.id.navigation_buy_id:
                        fragmentTransaction.replace(R.id.viewPager, new ComprarFragment()).commit();
                        return true;
                    case R.id.navigation_sell_id:
                        fragmentTransaction.replace(R.id.viewPager, new VenderFragment()).commit();
                        return true;
                    case R.id.navigation_change_id:
                        fragmentTransaction.replace(R.id.viewPager, new TrocarFragment()).commit();
                        return true;
                    case R.id.navigation_history_id:
                        fragmentTransaction.replace(R.id.viewPager, new HistoricoFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }


    FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        autenticacao = FirebaseConfig.getFirebaseAutentication();

        configuraBottomNavigationView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit();

    }

    //Metodo que cria o o BottomNavigation
    private void configuraBottomNavigationView() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation);

        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(true);
        habilitarNavegacao(bottomNavigationViewEx);

        //Configura item selecionado inicialmente no bottom navigation
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0  );
        menuItem.setChecked(true);
    }

}
