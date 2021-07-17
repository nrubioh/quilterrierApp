package com.example.appdeevaluacionmodulo03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb1, rb2;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

    }

    public void opciones (View v) {

        if (rb2.isChecked() == true){
            Intent a = new Intent(this, Loginctivity.class);
            startActivity(a);
        }else if (rb1.isChecked() == true){
            Intent b = new Intent(this, CrearCuentaActivity.class);
            startActivity(b);
        }
    }

}