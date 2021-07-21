package com.example.quilterrier

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.EditText
import android.os.Bundle
import com.example.quilterrier.R
import android.content.Intent
import android.view.View
import com.example.quilterrier.MainActivity
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
import java.lang.NullPointerException

class ActivityCrearCuenta : AppCompatActivity() {
    private var nAuth: FirebaseAuth? = null
    private var correo: EditText? = null
    private var contrasena: EditText? = null
    private var confimacontrasena: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        nAuth = FirebaseAuth.getInstance()
        correo = findViewById(R.id.correoedit)
        contrasena = findViewById(R.id.passwordedit)
        confimacontrasena = findViewById(R.id.passwordedit2)

        // try block to hide Action bar
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }
    }

    fun volver(v: View?) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    //Método para hacer una autentificación previa al logueo?? --> x Leo, esto entendí
    public override fun onStart() {
        super.onStart()
        //Chequea que el usuario se suscribe (no null) y updatea la UI de acuerdo a la suscripción
        val currentUser = nAuth!!.currentUser
        //upDateUi(currentUser); //Este método se usa una vez que el usuario ya está logueado, por lo mismo, al inicio se comenta para que no genere errores
    }

    //Método para registrar el usuario
    fun registrarUsuario(view: View?) {

        //Este primer condicional es para revisar que las dos contraseñas sean iguales, en caso contrario el registro fallará
        if (contrasena!!.text.toString() == confimacontrasena!!.text.toString()) {
            nAuth!!.createUserWithEmailAndPassword(
                correo!!.text.toString(),
                contrasena!!.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Registro exitoso, actualiza la UI del usuario con la información de registro
                        Toast.makeText(
                            applicationContext,
                            "Usuario creado con éxito",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = nAuth!!.currentUser
                        //si se regista correctamente, al dar click al boton que sea enviado a la main activity para que ahora escoja la opcion de loguearse con la cuenta recientemente creada
                        val i = Intent(applicationContext, MainActivity::class.java)
                        startActivity(i)

                        //updateUI(user); //comentado por la misma razon que el otro updateUI
                    } else {
                        //Si el registro falla, pasa lo siguiente  (arroja esta parte del método y mensajes)
                        Toast.makeText(
                            applicationContext,
                            "Fallo de autentificación",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null); //comentado por la misma razon que los otros dos updateUI
                    }
                }
        } else {
            Toast.makeText(this, "Ambas contraseñas no son iguales", Toast.LENGTH_SHORT).show()
        }
    }
}