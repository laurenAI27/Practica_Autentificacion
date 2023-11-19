package com.example.practica_autentificacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Firebase_Autentificacion extends AppCompatActivity {

    private Button btn_login, btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_autentificacion);

        getSupportActionBar().setTitle("Firebase Lauren");

        btn_login = findViewById(R.id.btlogin);
        btn_registrar = findViewById(R.id.btregistrar);

        btn_login.setOnClickListener(view -> {
            Intent screenLogin = new Intent(Firebase_Autentificacion.this, Login_Usuario.class);
            startActivity(screenLogin);
        });


        btn_registrar.setOnClickListener(view -> {
            Intent screenRegistrar = new Intent(Firebase_Autentificacion.this, Registrar_Usuario.class);
            startActivity(screenRegistrar);
        });
    }
}