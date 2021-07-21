package com.example.quilterrier

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.quilterrier.R
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import java.lang.NullPointerException

class resetPassword private constructor() : AppCompatActivity() {
    lateinit private var eTcorreo: EditText
    lateinit private var btnrestablecer: Button
    private var email =
        "" //Variable local que mantiene almacenado lo que el usuario escribe en el correo
    private var mAuth //Esta variable u objeto se crea para usarla al restablecer el password
            : FirebaseAuth? = null

    //Se crea un progress con un loader, diciendo que se está ejecutando el envío del correo de restablecimiento
    private var mDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        // try block to hide Action bar
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }

        //Instancio las variables en el onCreate
        mAuth = FirebaseAuth.getInstance()
        eTcorreo = findViewById(R.id.edittextcorreo)
        btnrestablecer = findViewById(R.id.btnrestablecer)
        mDialog = ProgressDialog(this) //Recibe como parametro el contexto = esta actividad = this.

        //se crea este método en el onCreate para que cuando el usuario de click al boton "restablecer" se active el metodo de cambiode contraseña
        btnrestablecer.setOnClickListener(View.OnClickListener {
            //Se trae al método lo que el usuario escribe en el editText (id edittextcorreo) y lo guarda en la variable anterior llamada email
            email = eTcorreo.getText().toString()

            //Se ejecuta una validación en donde comprueba que: si el campo editText (id edittextcorreo) NO esta vacio, ejecuta el método resetPassword
            //en caso contrario, arroja el mensaje de advertencia con el Toast.
            if (!email.isEmpty()) {
                //Si el usuario ingreso un correo se debe mostar el progress dialog instanciado antes
                mDialog!!.setMessage("Espera un momento...")
                //el mDialog.setCanceledOnTouchside (false) es para que el usuario no puede quitar el mensaje tocando en algun lado
                // mientras se esta ejecutando la función reset password
                mDialog!!.setCanceledOnTouchOutside(false)
                //el .show() va a hacer que los mensajes anteriores se muestren
                mDialog!!.show()
                //Luego del progress dialog, comienza a ejecutar el método (o al mismo tiempo, no es, es rápido esto, la cosas es que el mensaje y luego el método)
                resetPassword()
            } else {
                Toast.makeText(this@resetPassword, "Debe ingresar un correo", Toast.LENGTH_SHORT)
                    .show() //ADVERTENCIA DE TOAST
            }
        })
    }

    init {
        //El primer paso que se utiliza, es establecer el idioma en que debe llegar el correo para restablecer la contraseña, aunque
        //no se si ahora es necesario aún, de todos modos por el tutorial se establece en español (eso significa la parte de "es")
        mAuth!!.setLanguageCode("es")
        //Se establece el método que va a recibir el email a ser validado y a envviar el correo de restablecimiento de contraseña.
        mAuth!!.sendPasswordResetEmail(email)
            .addOnCompleteListener { task -> //Evalua si la tarea (task = enviar el correo al usuario) es exitosa, de ser así, Toast (a), si no ejecuta Toast (b)
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@resetPassword,
                        "Se ha enviado un correo a $email para reestablecer tu contraseña",
                        Toast.LENGTH_SHORT
                    ).show() //Toast (a)
                } else {
                    Toast.makeText(
                        this@resetPassword,
                        "No se pudo enviar el correo de reestablecer contraseña - culpa de Yayson",
                        Toast.LENGTH_SHORT
                    ).show() // Toast (b)
                }

                //en referencia al mDialog anterior, cuando la taresa termine de ejecutarse, el mensaje del Dialog se va ocultar, con el siguiente método
                mDialog!!.dismiss()
            }
    }
}