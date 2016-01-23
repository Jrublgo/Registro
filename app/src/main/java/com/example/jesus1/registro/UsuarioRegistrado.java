package com.example.jesus1.registro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

import static android.text.format.DateFormat.getDateFormat;

public class UsuarioRegistrado extends AppCompatActivity implements View.OnClickListener {

    TextView txt_sesion;
    TextView txt_userR;

    Button btn_mostrar;
    Button btn_min;
    Button btn_max;


    private ArrayList<Objeto> lista_objetos = new ArrayList<Objeto>();
    private ListView listView;
    private AdaptadorObjeto adaptadorObjeto;
    private final int CODE1 = 1;
    private final int CODE2 = 2;
    int post;
    String usuReg;


    boolean vista_btnmostrar = false;

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
        btn_max = (Button) findViewById(R.id.btn_max);
        btn_min = (Button) findViewById(R.id.btn_min);

        lista_objetos = CargarLista();
        listView = (ListView) findViewById(R.id.listView);
        adaptadorObjeto = new AdaptadorObjeto(this);
        listView.setAdapter(adaptadorObjeto);

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
        btn_min.setOnClickListener(this);
        btn_max.setOnClickListener(this);

        txt_sesion.setText("Última sesión: " + i.getStringExtra("dateSesion"));

        Toast.makeText(UsuarioRegistrado.this, "Registro finalizado con éxito.", Toast.LENGTH_SHORT).show();
       /* btn_mostrar.setVisibility(View.INVISIBLE);
        btn_mostrar.bringToFront();*/
    }
    @Override
    public void onClick(View v) {



        switch (v.getId()){
            case R.id.btn_mostrar:

                if (!vista_btnmostrar) {

                    btn_max.setVisibility(View.VISIBLE);
                    btn_min.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    vista_btnmostrar = true;

                }
                else if (vista_btnmostrar)
                {
                    btn_max.setVisibility(View.INVISIBLE);
                    btn_min.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                    vista_btnmostrar = false;
                }
                break;

                /*Intent i = new Intent(UsuarioRegistrado.this,Lista.class);
                i.putExtra("usuReg",txt_userR.getText());
                i.putExtra("lista",lista_objetos);
                startActivityForResult(i, CODE1);*/

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            String emisor = data.getStringExtra("emisor");
            String destinatario = data.getStringExtra("destinatario");
            int nota = (data.getIntExtra("nota", 0));
            String mensaje = data.getStringExtra("mensaje");
            Objeto nO = new Objeto(emisor, destinatario, nota, mensaje);
            if (requestCode == CODE1) {
                lista_objetos.set(post, nO);
            } else if (requestCode == CODE2) {
                lista_objetos.add(nO);
            }
            Toast.makeText(UsuarioRegistrado.this, "Nuevo elemento añadido", Toast.LENGTH_SHORT).show();
            //ista_objetos.add((Objeto) data.getSerializableExtra("NuevoObjeto"));
        } else if (resultCode == RESULT_CANCELED && data != null) {
            lista_objetos = (ArrayList<Objeto>) data.getSerializableExtra("lista");
        }
        adaptadorObjeto.notifyDataSetChanged();
    }
    private ArrayList<Objeto> CargarLista() {
        ArrayList<Objeto> aux = new ArrayList<Objeto>();
        aux.add(new Objeto("Jrublgo", "Javier Ruano", 3, "Este es el proyecto de Android"));
        aux.add(new Objeto("Prueba", "Prueba", "ESTE ES EL TEXTO"));
        aux.add(new Objeto("Yo", "A mi mismo", 2, "Acabar este proyecto"));
        aux.add(new Objeto("Yo", "Jrublgo", 1, "Mensaje a otro usuario"));
        aux.add(new Objeto("Yo", "Javier Ruano", "Quisiera revisar mi examen"));
        aux.add(new Objeto("Root", "Prueba", "Me estoy quedando sin ideas"));
        aux.add(new Objeto("Admin", "yo", 3, "Estás suspendido"));
        aux.add(new Objeto("Root", "A mi mismo", "No vale la pena"));
        aux.add(new Objeto("Profesor", "Alumno", "Tienes un 0"));
        aux.add(new Objeto("Jrublgo", "Prueba", 2, "Mensaje de poca importancia"));
        aux.add(new Objeto("Profesor", "Yo", 2, "Hablando conmigo mismo"));
        aux.add(new Objeto("Alumno", "Una persona", 1, "ESTE ES EL TEXTO"));
        return aux;
    }
    private class AdaptadorObjeto extends ArrayAdapter {

        Activity context;

        public AdaptadorObjeto(Activity context) {
            super(context, R.layout.fila_objetos, lista_objetos);
            // Layout y lista de objetos
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;

            ViewHolder holder;

            if (item == null) {
                // OnCreate
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.fila_objetos, null);

                holder = new ViewHolder();
                holder.txt_nota = (TextView) item.findViewById(R.id.txt_nota);
                holder.txt_grado = (TextView) item.findViewById(R.id.txt_grado);
                holder.txt_destinatario = (TextView) item.findViewById(R.id.txt_destinatario);
                holder.txt_emisor = (TextView) item.findViewById(R.id.txt_emisor);

                item.setTag(holder);
            } else {
                holder = (ViewHolder) item.getTag();
            }

            holder.txt_emisor.setText(lista_objetos.get(position).getEmisor());
            holder.txt_destinatario.setText(lista_objetos.get(position).getDestinatario());
            holder.txt_grado.setText(String.valueOf(lista_objetos.get(position).getNivel_urgencia()));
            holder.txt_nota.setText(lista_objetos.get(position).getTexto());
            return item;
        }
    }
    static class ViewHolder {
        TextView txt_emisor;
        TextView txt_destinatario;
        TextView txt_grado;
        TextView txt_nota;
    }
}
