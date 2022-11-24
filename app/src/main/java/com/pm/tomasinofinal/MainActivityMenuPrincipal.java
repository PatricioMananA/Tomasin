package com.pm.tomasinofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivityMenuPrincipal extends AppCompatActivity {

    Button cerrar , btnalumnos ,brncrud;
    FirebaseAuth mAuth;

    //
    DatabaseReference databaseReference;

    RecyclerView recyclerView;


    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_principal);

        btnalumnos = findViewById(R.id.btn_ir_alumnos);
        cerrar = findViewById(R.id.btn_cerrar);
        mAuth = FirebaseAuth.getInstance();
        brncrud = findViewById(R.id.btn_crud);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivityMenuPrincipal.this,Login.class));
            }
        });


        btnalumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivityMenuPrincipal.this,alumnos.class));
            }
        });

        brncrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityMenuPrincipal.this,MainActivity.class));
                finish();
            }
        });





    }

}