package com.example.practica_autentificacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Conexion_BBDD extends AppCompatActivity {

    private Button btn_escribir, btn_leer;
    private EditText et_info_guardado;
    private TextView tv_info_lectura;

    private FirebaseDatabase basededatos;
    private DatabaseReference miReferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_bbdd);

        getSupportActionBar().setTitle("Firebase Lauren");

        btn_escribir = findViewById(R.id.btguardar);
        btn_leer = findViewById(R.id.btleer);
        et_info_guardado = findViewById(R.id.et_bbdd_guardado);
        tv_info_lectura = findViewById(R.id.tv_bbdd_leer);

        //Llamada a la base de datos creada en la plataforma Firebase
        basededatos = FirebaseDatabase.getInstance();
        //LLamada a la referencia de la base de datos por medio del hijo que es la primera rama
        // de la plataforma Firebase
        miReferencia = basededatos.getReference().child("MiRama");
        btn_escribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dato = et_info_guardado.getText().toString().trim();
                if(!TextUtils.isEmpty(dato)){
                    escrituraDato(dato);
                }else{
                    Toast.makeText(Conexion_BBDD.this,"Por favor, introduzca un dato", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lecturaValor();
            }
        });

    }

    //Metodo de escritura del valor del nodo secundario de nuestra base de datos
    private void escrituraDato(String value){
        //Declaración e instanciación de la variable que se le asigna la clave del nodo de nuestra base de datos
       String password = "MyPass";
       //Condición para comprobar si la clave del nodo si es distinto de vacio, se puede hacer con la referencia
        // cogiendo su clave, en este caso la variable creada arriba
        if(miReferencia.getKey() != null){
            //A la referencia se le asigna la clave del nodo hijo de nuestra base de datos y  su el cambio del valor proporcionado por el usuario
            miReferencia.child(password).setValue(value);

            Toast.makeText(Conexion_BBDD.this, "Valor correctamente guardado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Conexion_BBDD.this, "Lo siento, el valor no se ha guardado", Toast.LENGTH_SHORT).show();
        }
    }


    //Metodo de la lectura del valor del nodo secundario de nuestra base de datos
    private void lecturaValor(){
        //La referencia porporcionada de la base de datos, inicia el evento para la muestra del valor del nodo
        miReferencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Declaración de for each que recorre los nodos(hijo) de la base de datos
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    //Nuestra variable recibe el valor iterado de for each a una cadena
                    String valor_dato = dataSnapshot1.getValue(String.class);
                    //Comprobamos que el dato pasado es distinto de vacío y luego se introduce el dato en el textView
                    if(valor_dato !=null){
                        tv_info_lectura.setText(valor_dato);
                    } else{
                        Toast.makeText(Conexion_BBDD.this, "El dato proporcionado no ha sido guardado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            // No se ha podido proceder a la lectura del valor del nodo(dato) por un fallo. Ejemplo: error al conectarse
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Conexion_BBDD.this, "Problema para la obtención del dato", Toast.LENGTH_SHORT).show();
            }
        });
    }
}