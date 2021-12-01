package com.example.claseprogfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private TextView correo, contraseña;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        correo = findViewById(R.id.loginCorreo);
        contraseña = findViewById(R.id.loginContraseña);

        mAuth = FirebaseAuth.getInstance();


    }

    public void iniciarSesión(View view){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser usuario = mAuth.getCurrentUser();

                    Toast.makeText(MainActivity2.this, "Usuario: "+usuario.getUid(), Toast.LENGTH_SHORT).show();

                    Intent intento = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intento);

                } else {
                    Toast.makeText(MainActivity2.this, "No fue posible iniciar sesión con los datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}