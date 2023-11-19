package com.example.practica_autentificacion;

import static android.content.ContentValues.TAG;

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

public class Registrar_Usuario extends AppCompatActivity {

    private Button btn_registro;
    private FirebaseAuth autentificacion;
    private EditText etxt_mail, etxt_password;

    private TextView tv_confirmacionUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        getSupportActionBar().setTitle("Firebase Lauren");

        btn_registro = findViewById(R.id.btregistro);
        etxt_mail = findViewById(R.id.etxtEmailAddress);
        etxt_password = findViewById(R.id.etxtPassword);
        tv_confirmacionUsuario = findViewById(R.id.tv_registroCofirmado);

        btn_registro.setOnClickListener(view -> {
            String email = etxt_mail.getText().toString();
            String password = etxt_password.getText().toString();
            createAccount(email, password);
        });
    }
    private void createAccount(String email, String password) {
// [START create_user_with_email]
        autentificacion.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {

// Sign in success, update UI with the signed-in user's information

                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(Registrar_Usuario.this, "Usuario registrado correctamente",
                                            Toast.LENGTH_LONG).show();
                                    tv_confirmacionUsuario.setText("Usuario registrado correctamente");
                                            FirebaseUser user = autentificacion.getCurrentUser();

//updateUI(user);

                                } else {
// If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure",
                                                    task.getException());
                                    Toast.makeText(Registrar_Usuario.this, "Usuario no registrado",
                                            Toast.LENGTH_SHORT).show();
                                    tv_confirmacionUsuario.setText("Usuario no registrado");
//updateUI(null);
                                }
                            }
                        });
// [END create_user_with_email]
    }
}