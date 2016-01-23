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

    private final int CODE1 = 1;
    private final int CODE2 = 2;

    private SharedPreferences sp;
    int valorCbox;
    // 1 == True | 0 == False

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);

        txt_userR = (TextView) findViewById(R.id.txt_userR);
        txt_sesion = (TextView) findViewById(R.id.txt_datesesion);
        btn_mostrar = (Button) findViewById(R.id.btn_mostrar);

        Intent i = getIntent();
        sp = getSharedPreferences("datos", MODE_PRIVATE);
        valorCbox = i.getIntExtra("valor",0);

        if (valorCbox == 0)
        {
            txt_userR.setText(i.getStringExtra("user"));
        }
        else if (valorCbox == 1) {
            txt_userR.setText(sp.getString("userSP",""));
        }

        btn_mostrar.setOnClickListener(this);
        txt_sesion.setText("Última sesión: " + i.getStringExtra("dateSesion"));
        Toast.makeText(UsuarioRegistrado.this, "Registro finalizado con éxito.", Toast.LENGTH_SHORT).show();
        btn_mostrar.setVisibility(View.INVISIBLE);
        btn_mostrar.bringToFront();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mostrar:
                Intent i = new Intent(UsuarioRegistrado.this,Lista.class);
                i.putExtra("usuReg",txt_userR.getText());
                startActivityForResult(i,1);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (resultCode == RESULT_OK && data != null) {
            String emisor = data.getStringExtra("emisor");
            String destinatario = data.getStringExtra("destinatario");
            int nota = (data.getIntExtra("nota", 0));
            String mensaje = data.getStringExtra("mensaje");
            Objeto nO = new Objeto(emisor, destinatario, nota, mensaje);
            if (requestCode == CODE1 ) {
                lista_objetos.set(post, nO);
            } else if (requestCode == CODE2) {
                lista_objetos.add(nO);
            }
            adaptadorObjeto.notifyDataSetChanged();
            Toast.makeText(Lista.this, "Nuevo elemento añadido", Toast.LENGTH_SHORT).show();
            //ista_objetos.add((Objeto) data.getSerializableExtra("NuevoObjeto"));
        }
        else if (resultCode == RESULT_CANCELED) {
*/
        }
}
