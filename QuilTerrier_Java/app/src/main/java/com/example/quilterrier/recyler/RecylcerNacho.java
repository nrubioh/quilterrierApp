package com.example.quilterrier.recyler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.quilterrier.R;

public class RecylcerNacho extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterRecylcer mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer_nacho);
    }
}