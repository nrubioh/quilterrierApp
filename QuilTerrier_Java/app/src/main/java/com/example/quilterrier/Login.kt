package com.example.quilterrier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private var correo: EditText? = null
    private var contrasena: EditText? = null
    private var nAuth //objeto de la clase Firebase
            : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        correo = findViewById(R.id.correoedit)
        contrasena = findViewById(R.id.passwordedit)
        nAuth = FirebaseAuth.getInstance() //se instancia el objeto


        // try block to hide Action bar
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }
    }

    fun restablecerPassword(v: View?) {
        val i = Intent(this, resetPassword::class.java)
        startActivity(i)
    }

    fun crearCuenta(v: View?) {
        val i = Intent(this, ActivityCrearCuenta::class.java)
        startActivity(i)
    }

    //Si inicia la app, que revise si ya tiene iniciada la sesión
    public override fun onStart() {
        super.onStart()
        //cheque si ya esta logueado y actualiza la UI si ese es el caso
        val currentUser = nAuth!!.currentUser
        //UpdateUI(currenUser); //comentado por que por ahora no se va a utilizar a menos que alguien ya esté logueado
    }

    //Acá va el método que lo que va a permitir es iniciar la sesión (antes hay que registrarse, si no es así, no resulta)
    fun iniciarSesion(view: View?) {

        // String contrasena = getText().toString();
        // String correo = getText().toString();

        /*if (!contrasena.isEmpty() && !correo.isEmpty()){  */
        nAuth!!.signInWithEmailAndPassword(correo!!.text.toString(), contrasena!!.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Login exitoso, actualiza la UI del usuario con la información de registro
                    val user = nAuth!!.currentUser
                    //updateUI(user);
                    //si se loguea correctamente, al dar click al boton que sea enviado a la activity de inicio de perdidos y adopciones en la app
                    //Este Intent tiene que ser modificado para que te lleve al inicio de la app
                    val i = Intent(applicationContext, InicioApp::class.java)
                    startActivity(i)
                } else {
                    //Si el logueo falla, tirale un mensaje al usuario por equivocarse
                    Toast.makeText(applicationContext, "Logueo falló", Toast.LENGTH_SHORT).show()
                    //updateUI(null);
                }
            }
        /* }else{

        Toast.makeText(getApplicationContext(), "Debes llenar todos los campos solicitados", Toast.LENGTH_SHORT).show();

    }*/
    }
}