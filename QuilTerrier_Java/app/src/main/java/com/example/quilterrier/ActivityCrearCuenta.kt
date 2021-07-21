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

public class ActivityCrearCuenta extends AppCompatActivity {

    private FirebaseAuth nAuth;
    private EditText correo, contrasena, confimacontrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        nAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correoedit);
        contrasena = findViewById(R.id.passwordedit);
        confimacontrasena = findViewById(R.id.passwordedit2);

        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

    }

    public void volver(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    //Método para hacer una autentificación previa al logueo?? --> x Leo, esto entendí

    public void onStart() {

        super.onStart();
        //Chequea que el usuario se suscribe (no null) y updatea la UI de acuerdo a la suscripción
        FirebaseUser currentUser = nAuth.getCurrentUser();
        //upDateUi(currentUser); //Este método se usa una vez que el usuario ya está logueado, por lo mismo, al inicio se comenta para que no genere errores
    }

    //Método para registrar el usuario

    public void registrarUsuario (View view) {

        //Este primer condicional es para revisar que las dos contraseñas sean iguales, en caso contrario el registro fallará

        if (contrasena.getText().toString().equals(confimacontrasena.getText().toString())) {

            nAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //Registro exitoso, actualiza la UI del usuario con la información de registro
                                Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = nAuth.getCurrentUser();
                                //si se regista correctamente, al dar click al boton que sea enviado a la main activity para que ahora escoja la opcion de loguearse con la cuenta recientemente creada
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);

                                //updateUI(user); //comentado por la misma razon que el otro updateUI
                            } else {
                                //Si el registro falla, pasa lo siguiente  (arroja esta parte del método y mensajes)

                                Toast.makeText(getApplicationContext(), "Fallo de autentificación", Toast.LENGTH_SHORT).show();
                                //updateUI(null); //comentado por la misma razon que los otros dos updateUI
                            }

                        }
                    });

        } else {
            Toast.makeText(this, "Ambas contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        }
    }
}