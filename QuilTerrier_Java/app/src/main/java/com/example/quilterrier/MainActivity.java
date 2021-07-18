package com.example.quilterrier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText correo, contrasena;
    private FirebaseAuth nAuth;   //objeto de la clase Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.correoedit);
        contrasena = findViewById(R.id.passwordedit);

        nAuth = FirebaseAuth.getInstance(); //se instancia el objeto

    }

    public void restablecerPassword (View v) {

        Intent i = new Intent(this, resetPassword.class);
        startActivity(i);

    }

    public void crearCuenta (View v) {

        Intent i = new Intent(this, ActivityCrearCuenta.class);
        startActivity(i);

    }

    //Si inicia la app, que revise si ya tiene iniciada la sesión

    public void onStart (){

        super.onStart();
        //cheque si ya esta logueado y actualiza la UI si ese es el caso
        FirebaseUser currentUser = nAuth.getCurrentUser();
        //UpdateUI(currenUser); //comentado por que por ahora no se va a utilizar a menos que alguien ya esté logueado

    }

    //Acá va el método que lo que va a permitir es iniciar la sesión (antes hay que registrarse, si no es así, no resulta)

    public void iniciarSesion (View view) {

        // String contrasena = getText().toString();
        // String correo = getText().toString();

        /*if (!contrasena.isEmpty() && !correo.isEmpty()){  */
        nAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Login exitoso, actualiza la UI del usuario con la información de registro
                            FirebaseUser user = nAuth.getCurrentUser();
                            //updateUI(user);
                            //si se loguea correctamente, al dar click al boton que sea enviado a la activity de inicio de perdidos y adopciones en la app
                            //Este Intent tiene que ser modificado para que te lleve al inicio de la app
                            Intent i = new Intent(getApplicationContext(), InicioApp.class);
                            startActivity(i);

                        }else{
                            //Si el logueo falla, tirale un mensaje al usuario por equivocarse
                            Toast.makeText(getApplicationContext(), "Logueo falló", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
   /* }else{

        Toast.makeText(getApplicationContext(), "Debes llenar todos los campos solicitados", Toast.LENGTH_SHORT).show();

    }*/
    }

}