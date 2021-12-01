package com.example.claseprogfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {

    private TextView mensaje, idMensaje;

    FirebaseAuth mAuth;
    FirebaseUser usuarioActual;

    FirebaseDatabase database;
    DatabaseReference reference;

    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mensaje = findViewById(R.id.contenidoMensaje);
        idMensaje = findViewById(R.id.contenidoID);

        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

    }

    public void crearMensaje(View view){
        contador++;
        reference.child("mensajes").child(usuarioActual.getUid()).child("mensaje_"+contador).child("texto").setValue(mensaje.getText().toString());
        Toast.makeText(this, "Mensaje guardado con id: "+contador, Toast.LENGTH_SHORT).show();
    }

    public void recuperarMensaje(View view){
        DatabaseReference referenciaMensaje = FirebaseDatabase.getInstance().getReference("mensajes").child(usuarioActual.getUid()).child("mensaje_"+idMensaje.getText().toString());
        //DatabaseReference mensajes = referenciaMensaje.child("mensaje_"+idMensaje.getText().toString());
        referenciaMensaje.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity3.this, "Mensaje: "+snapshot.child("texto").getValue(String.class), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity3.this, "Mensaje no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}