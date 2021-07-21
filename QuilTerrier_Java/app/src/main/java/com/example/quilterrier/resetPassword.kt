package com.example.quilterrier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword extends AppCompatActivity {

    private EditText eTcorreo;
    private Button btnrestablecer;

    private String email = ""; //Variable local que mantiene almacenado lo que el usuario escribe en el correo
    private FirebaseAuth mAuth; //Esta variable u objeto se crea para usarla al restablecer el password

    //Se crea un progress con un loader, diciendo que se está ejecutando el envío del correo de restablecimiento
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        //Instancio las variables en el onCreate
        mAuth = FirebaseAuth.getInstance();
        eTcorreo = findViewById(R.id.edittextcorreo);
        btnrestablecer = findViewById(R.id.btnrestablecer);
        mDialog = new ProgressDialog(this); //Recibe como parametro el contexto = esta actividad = this.

        //se crea este método en el onCreate para que cuando el usuario de click al boton "restablecer" se active el metodo de cambiode contraseña
        btnrestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                //Se trae al método lo que el usuario escribe en el editText (id edittextcorreo) y lo guarda en la variable anterior llamada email
                email = eTcorreo.getText().toString();

                //Se ejecuta una validación en donde comprueba que: si el campo editText (id edittextcorreo) NO esta vacio, ejecuta el método resetPassword
                //en caso contrario, arroja el mensaje de advertencia con el Toast.
                if (!email.isEmpty()){
                    //Si el usuario ingreso un correo se debe mostar el progress dialog instanciado antes
                    mDialog.setMessage("Espera un momento...");
                    //el mDialog.setCanceledOnTouchside (false) es para que el usuario no puede quitar el mensaje tocando en algun lado
                    // mientras se esta ejecutando la función reset password
                    mDialog.setCanceledOnTouchOutside(false);
                    //el .show() va a hacer que los mensajes anteriores se muestren
                    mDialog.show();
                    //Luego del progress dialog, comienza a ejecutar el método (o al mismo tiempo, no es, es rápido esto, la cosas es que el mensaje y luego el método)
                    resetPassword();
                }else{
                    Toast.makeText(resetPassword.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show(); //ADVERTENCIA DE TOAST
                }
            }
        });
    }

    private void resetPassword (){
        //El primer paso que se utiliza, es establecer el idioma en que debe llegar el correo para restablecer la contraseña, aunque
        //no se si ahora es necesario aún, de todos modos por el tutorial se establece en español (eso significa la parte de "es")
        mAuth.setLanguageCode("es");
        //Se establece el método que va a recibir el email a ser validado y a envviar el correo de restablecimiento de contraseña.
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                //Evalua si la tarea (task = enviar el correo al usuario) es exitosa, de ser así, Toast (a), si no ejecuta Toast (b)
                if(task.isSuccessful()){
                    Toast.makeText(resetPassword.this, "Se ha enviado un correo a "+email+" para reestablecer tu contraseña", Toast.LENGTH_SHORT).show(); //Toast (a)
                }else{
                    Toast.makeText(resetPassword.this,"No se pudo enviar el correo de reestablecer contraseña - culpa de Yayson", Toast.LENGTH_SHORT).show(); // Toast (b)
                }

                //en referencia al mDialog anterior, cuando la taresa termine de ejecutarse, el mensaje del Dialog se va ocultar, con el siguiente método
                mDialog.dismiss();
            }
        });
    }
}