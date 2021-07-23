package com.example.quilterrier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quilterrier.recyler.AdapterRecylcer
import com.example.quilterrier.recyler.RecylcerNacho

// git pull origin branchName ==> Permite trabajar en otra rama
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var nombre: Array<String>
    lateinit var tipo: Array<String>
    lateinit var ubicacion: Array<String>
    lateinit var especie: Array<String>
    var images = intArrayOf(
        R.drawable.uno, R.drawable.dos, R.drawable.tres,
        R.drawable.cuatro, R.drawable.cinco
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        nombre = resources.getStringArray(R.array.nombre_perro)
        tipo = resources.getStringArray(R.array.tipo)
        ubicacion = resources.getStringArray(R.array.ubicacion)
        especie = resources.getStringArray(R.array.especie)
        val adapterRecylcer = AdapterRecylcer(
            this,
            nombre, tipo, ubicacion, especie, images
        )
        recyclerView.setAdapter(adapterRecylcer)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
    }

    fun startLogin(v: View?) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}