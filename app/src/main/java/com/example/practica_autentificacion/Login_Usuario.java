package com.example.practica_autentificacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Usuario extends AppCompatActivity {

    //Declaramos las variables
private Button btn_loguear, btn_bd;
private TextView mensajeLogin;
private EditText etxt_mail, etxtpass;
private FirebaseAuth autentificado;
private boolean userLoggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        getSupportActionBar().setTitle("Firebase Lauren");

        //Instanciamos las variables que vamos a usar
        mensajeLogin = findViewById(R.id.tv_loginAceptado);
        etxt_mail = findViewById(R.id.etEmailAddress);
        etxtpass = findViewById(R.id.etPassword);
        autentificado = FirebaseAuth.getInstance();
        btn_loguear = findViewById(R.id.btregistlogin);
        btn_bd = findViewById(R.id.btbbdd);

        //Asignamos el boton para inicar sesión con el onClickListener
        btn_loguear.setOnClickListener(v -> {
            String emailLog = etxt_mail.getText().toString();
            String passwordLog = etxtpass.getText().toString();
            signIn(emailLog, passwordLog);
        });

        btn_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Código de Alba comprobació del usuario si tiene cuenta para loguearse
                if(userLoggedIn== true) {
                    Intent pantallaBBDD = new Intent(Login_Usuario.this, Conexion_BBDD.class);
                    startActivity(pantallaBBDD);
                } else if (userLoggedIn==false) {
                    Toast.makeText(Login_Usuario.this, "Necesitas estar logueado para poder entrar en la BD", Toast.LENGTH_LONG).show();
                }
                
            }
        });

    }

    private void signIn(String email, String password) {

        autentificado.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {

                                    Log.d("Login USUARIO", "signInWithEmail:success");
                                    Toast.makeText(Login_Usuario.this, "Autentication con exito.", Toast.LENGTH_LONG).show();
                                    mensajeLogin.setText("Login con exito");
                                    FirebaseUser user = autentificado.getCurrentUser();
                                    userLoggedIn = true;
                                } else {

                                    Log.w("Login USUARIO", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login_Usuario.this, "Autenticacion fallida.", Toast.LENGTH_LONG).show();
                                    mensajeLogin.setText("Login fallido");

                                }
                            }
                        });

    }
}