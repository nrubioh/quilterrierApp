package com.example.quilterrier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class InicioApp extends AppCompatActivity {

    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_app);
    }

    public void cerrarSesion (View view) {

        nAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}