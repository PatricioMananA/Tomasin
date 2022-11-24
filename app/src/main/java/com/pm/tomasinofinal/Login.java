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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button btn_ingreso, btnnuevo , btn_sqlite;
    EditText correo, contrasena;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        btn_ingreso = findViewById(R.id.btn_ingresar);
        btnnuevo = findViewById(R.id.btn_registrarse);
        btn_sqlite = findViewById(R.id.btn_contacto);
        //btn_mapa = findViewById(R.id.btn_mapa);

        btn_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Login.this,Maincontacto.class));


            }
        });
        btn_ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = correo.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();
                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(Login.this, "ingresa los datos", Toast.LENGTH_LONG).show();
                } else {
                    LoginUser(emailUser, passUser);
                }
            }

            private void LoginUser(String emailUser, String passUser) {
                mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           // finish();
                            startActivity(new Intent(Login.this, MainActivityMenuPrincipal.class));
                            Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "Credenciales invalidas", Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "error al iniciar sesion", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,NuevoUsuario.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null ){
            startActivity(new Intent(Login.this,MainActivity.class));
            //finish();
        }
    }

    public void Irmapa (View view){
        startActivity(new Intent(Login.this,MapsActivity.class));
    }

    public void IrContacto (View form){
        startActivity(new Intent(Login.this,MainActivity.class));
    }
}
