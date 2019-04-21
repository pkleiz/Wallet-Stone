package com.pedrokleiz.walletstone.Config;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConfig {
    private static FirebaseAuth firebaseConfig_autentication;

    //retorna a instancia do firebaseauth
    public static FirebaseAuth getFirebaseAutentication() {
        if (firebaseConfig_autentication == null) {
            firebaseConfig_autentication = FirebaseAuth.getInstance();
        }
        return firebaseConfig_autentication;
    }
}