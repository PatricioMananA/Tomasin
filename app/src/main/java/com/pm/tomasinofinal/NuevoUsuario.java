package com.pm.tomasinofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NuevoUsuario extends AppCompatActivity {

    Button btn_registrar;
    EditText nuevousuariocorreo, nuevousuariocontrasena;
    private String correo;
    private String contrasena;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        nuevousuariocorreo = findViewById(R.id.nuevoUserCorreo);
        nuevousuariocontrasena = findViewById(R.id.nuevoUserContrasena);
        btn_registrar = findViewById(R.id.btn_agregarusuario);



        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = nuevousuariocorreo.getText().toString();
                contrasena = nuevousuariocontrasena.getText().toString();

                if (!correo.isEmpty() && !contrasena.isEmpty()){
                    registrarusuario();
                }else {
                    Toast.makeText(NuevoUsuario.this, "ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                }

            }


        });
    }

    private void registrarusuario() {
        mAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("correo", correo);
                    map.put("contrasena", contrasena);

                    String id = mAuth.getCurrentUser().getUid();
                    startActivity(new Intent(NuevoUsuario.this,MainActivityMenuPrincipal.class));
                    finish();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(NuevoUsuario.this,MainActivityMenuPrincipal.class));
                                //finish();
                            }else{
                                Toast.makeText(NuevoUsuario.this, "no se registro el usuario111", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(NuevoUsuario.this, "no se registro el usuario", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}