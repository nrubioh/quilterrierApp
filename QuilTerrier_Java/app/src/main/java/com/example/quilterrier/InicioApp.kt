package com.example.quilterrier

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class InicioApp : AppCompatActivity() {
    var nAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_app)
    }

    fun cerrarSesion(view: View?) {
        nAuth!!.signOut()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

        // try block to hide Action bar
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }
    }
}