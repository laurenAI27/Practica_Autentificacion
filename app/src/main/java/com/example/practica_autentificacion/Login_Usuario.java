package com.example.practica_autentificacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

private Button btn_loguear;
private TextView mensajeLogin;
private EditText etxt_mail, etxtpass;
private FirebaseAuth autentificado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        mensajeLogin = findViewById(R.id.tv_loginAceptado);
        etxt_mail = findViewById(R.id.etEmailAddress);
        etxtpass = findViewById(R.id.etPassword);

        btn_loguear = findViewById(R.id.btregistlogin);


    }

    private void signIn(String email, String password) {
// [START sign_in_with_email]
        autentificado.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {

// Sign in success, update UI with the signed-in user's information

                                    Log.d("Login USUARIO",
                                            "signInWithEmail:success");
                                    Toast.makeText(Login_Usuario.this, "Autentication con exito.", Toast.LENGTH_LONG).show();
                                    mensajeLogin.setText("Login con exito");
                                    FirebaseUser user = autentificado.getCurrentUser();
// updateUI(user);
                                } else {
// If sign in fails, display a message to the user.
                                            Log.w("Login USUARIO", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login_Usuario.this, "Autenticacion fallida.", Toast.LENGTH_LONG).show();
                                    mensajeLogin.setText("Login fallido");
// updateUI(null);
                                }
                            }
                        });
// [END sign_in_with_email]
    }
}