package com.example.jesus1.registro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

import static android.text.format.DateFormat.getDateFormat;

public class UsuarioRegistrado extends AppCompatActivity implements View.OnClickListener {

    TextView txt_sesion;
    TextView txt_userR;
    Button btn_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);
        Toast.makeText(UsuarioRegistrado.this, "Registro finalizado con éxito", Toast.LENGTH_SHORT).show();
        txt_userR = (TextView) findViewById(R.id.txt_userR);

        txt_userR.setText(getIntent().getStringExtra("user"));
        txt_sesion = (TextView) findViewById(R.id.txt_datesesion);
        txt_sesion.setText("Última sesión: " + getIntent().getStringExtra("dateSesion"));

        btn_mostrar = (Button) findViewById(R.id.btn_mostrar);
        btn_mostrar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mostrar:
                Intent i = new Intent(UsuarioRegistrado.this,Lista.class);
                i.putExtra("usuReg",txt_userR.getText());
                startActivity(i);
                break;
        }
    }
}
