package com.example.jesus1.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_user;
    private TextView txt_pass;
    private Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Lo que coge para el getresources.getstring en UsuarioRegistrado
        txt_user = (TextView) findViewById(R.id.etxt_user);
        txt_pass = (TextView) findViewById(R.id.etxt_pass);
        btn_iniciar = (Button) findViewById(R.id.btn_sesion);
        btn_iniciar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sesion:
                if (txt_user.getText().length() == 0 || txt_pass.length() == 0) {
                    Toast.makeText(Registro.this, "Campos Inválidos. Intentelo de nuevo.", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    // Escribir TOAST y poner crear un nuevo TOAST
                    Toast.makeText(Registro.this, "Registrándose...", Toast.LENGTH_SHORT).show();

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat(); //called without pattern
                    Intent i = new Intent(Registro.this,UsuarioRegistrado.class);
                    i.putExtra("user",txt_user.getText().toString());
                    i.putExtra("pass",txt_pass.getText().toString());
                    i.putExtra("dateSesion", df.format(c.getTime()).toString());
                    startActivity(i);
                    break;
                }
        }
    }
}
