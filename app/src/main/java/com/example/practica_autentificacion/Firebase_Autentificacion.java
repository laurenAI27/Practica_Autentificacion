package com.example.practica_autentificacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Firebase_Autentificacion extends AppCompatActivity {

    //Declaración de las variables de los botones
    private Button btn_login, btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_autentificacion);

        //Cambiamos el texto del actionbar
        getSupportActionBar().setTitle("Firebase Lauren");

        //Instanciación de los botones
        btn_login = findViewById(R.id.btlogin);
        btn_registrar = findViewById(R.id.btregistrar);

        //Asociamos los botones al ClickListener para que nos lleva a otra pantalla
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