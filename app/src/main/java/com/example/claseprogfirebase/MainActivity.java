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

public class MainActivity extends AppCompatActivity {

    private TextView correo, contraseña;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.inputCorreo);
        contraseña = findViewById(R.id.inputContraseña);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioActual = mAuth.getCurrentUser();

        if(usuarioActual != null){
            Toast.makeText(this, "Usuario ID: "+usuarioActual.getUid()+ " / Nombre: "+usuarioActual.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void crearCuenta(View view){
        mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser usuarioNuevo = mAuth.getCurrentUser();
                    mensajeOk(usuarioNuevo.getUid());
                } else {
                    mensajeError();
                }
            }
        });
    }


    private void mensajeOk(String idUsuario){
        Toast.makeText(this, "Usuario nuevo creado. ID: "+idUsuario, Toast.LENGTH_SHORT).show();
    }

    private void mensajeError(){
        Toast.makeText(this, "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
    }

    public void irAIniciarSesion(View view){
        Intent intento = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intento);
    }
}