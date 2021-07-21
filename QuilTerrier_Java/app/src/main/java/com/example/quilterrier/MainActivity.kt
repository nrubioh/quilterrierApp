package com.example.quilterrier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quilterrier.recyler.RecylcerNacho

// git pull origin branchName ==> Permite trabajar en otra rama
class MainActivity : AppCompatActivity() {
    var btn_recycler: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, RecylcerNacho::class.java)
        startActivity(intent)


        //btn_recycler = findViewById(R.id.btnrecycler);
    }

    fun iraRecycler(view: View?) {
        val intent = Intent(this, RecylcerNacho::class.java)
        startActivity(intent)
    }
}