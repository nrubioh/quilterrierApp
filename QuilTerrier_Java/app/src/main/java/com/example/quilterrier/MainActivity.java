package com.example.quilterrier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quilterrier.recyler.RecylcerNacho;

// git pull origin branchName ==> Permite trabajar en otra rama

public class MainActivity extends AppCompatActivity {
    Button btn_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, RecylcerNacho.class);
        startActivity(intent);


        //btn_recycler = findViewById(R.id.btnrecycler);

    }

    public void iraRecycler(View view){
        Intent intent = new Intent(this, RecylcerNacho.class);
        startActivity(intent);



    }

}