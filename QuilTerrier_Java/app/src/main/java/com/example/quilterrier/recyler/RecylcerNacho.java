package com.example.quilterrier.recyler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.quilterrier.R;

public class RecylcerNacho extends AppCompatActivity {
    RecyclerView recyclerView;

    String nombre[], tipo[], ubicacion[] , especie[];
    int images[] ={R.drawable.uno, R.drawable.dos, R.drawable.tres,
            R.drawable.cuatro, R.drawable.cinco};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer_nacho);

        recyclerView = findViewById(R.id.recyclerview);

        nombre = getResources().getStringArray(R.array.nombre_perro);
        tipo = getResources().getStringArray(R.array.tipo);
        ubicacion = getResources().getStringArray(R.array.ubicacion);
        especie = getResources().getStringArray(R.array.especie);
        AdapterRecylcer adapterRecylcer = new AdapterRecylcer(RecylcerNacho.this,
                nombre, tipo, ubicacion, especie, images);
        recyclerView.setAdapter(adapterRecylcer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}


//                {
////            @Override
////            public void onBindViewHolder(@NonNull  AdapterRecylcer.MyViewHolder holder, int position) {
////
////            }
//        };