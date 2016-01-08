package com.example.jesus1.registro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.text.format.DateFormat.getDateFormat;

public class UsuarioRegistrado extends AppCompatActivity {

    //Date lastSesion = new Date();
   // DateFormat dateFormat = getDateFormat(getApplicationContext());

    TextView txt_sesion;
    TextView txt_userR;


   // Calendar c = Calendar.getInstance();
   // SimpleDateFormat df = new SimpleDateFormat(); //called without pattern
    // Que lo envie registro.


    /*
    Date date = new Date(location.getTime());
    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
    mTimeText.setText("Time: " + dateFormat.format(date));
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);
        Toast.makeText(UsuarioRegistrado.this, "Registro finalizado con éxito", Toast.LENGTH_SHORT).show();
        txt_userR = (TextView) findViewById(R.id.txt_userR);
        txt_userR.setText(getIntent().getStringExtra("user"));
        txt_sesion = (TextView) findViewById(R.id.txt_datesesion);
        txt_sesion.setText("Última sesión: " + getIntent().getStringExtra("dateSesion"));

    }
}
