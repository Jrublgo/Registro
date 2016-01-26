package com.example.jesus1.registro;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    private TextView txt_sesion;
    private TextView txt_userR;
    private LinearLayout ll_panel;
    private Button btn_min;
    private Button btn_max;
    private ArrayList<Objeto> lista_objetos = new ArrayList<Objeto>();
    private ListView listView;
    private AdaptadorObjeto adaptadorObjeto;
    private final int CODE1 = 1;
    private final int CODE2 = 2;
    private static final int CODE3 = 3;
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
        ll_panel = (LinearLayout) findViewById(R.id.ll_panel);
        txt_userR = (TextView) findViewById(R.id.txt_userR);
        txt_sesion = (TextView) findViewById(R.id.txt_datesesion);
        String usuReg = txt_userR.toString();
        btn_max = (Button) findViewById(R.id.btn_max);
        btn_min = (Button) findViewById(R.id.btn_vaciar);
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

        btn_min.setOnClickListener(this);
        btn_max.setOnClickListener(this);

        txt_sesion.setText("Sesión iniciada " + i.getStringExtra("dateSesion"));

        Toast.makeText(UsuarioRegistrado.this, "Registro finalizado con éxito.", Toast.LENGTH_SHORT).show();
        //btn_mostrar.bringToFront();*/
        registerForContextMenu(listView);
        ll_panel.setVisibility(View.INVISIBLE);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;

        switch(item.getItemId()) {
            case R.id.action_add:
                i = new Intent(UsuarioRegistrado.this,NuevoObjeto.class);
                i.putExtra("emisor",txt_userR.getText());
                startActivityForResult(i,CODE2);
                break;
            case R.id.action_contact:
                i = new Intent(UsuarioRegistrado.this,Contacto.class);
                startActivity(i);
                break;
            case R.id.action_exit:
                finish();
                break;
            case R.id.action_see:
                if (!vista_btnmostrar) {

                   // btn_max.setVisibility(View.VISIBLE);
                   // btn_min.setVisibility(View.VISIBLE);
                    ll_panel.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    vista_btnmostrar = true;

                }
                else if (vista_btnmostrar)
                {
                    ll_panel.setVisibility(View.INVISIBLE);
                    //btn_max.setVisibility(View.INVISIBLE);
                    //btn_min.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                    vista_btnmostrar = false;
                }
                break;

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_opciones_lista, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()) {

            case R.id.opcEditar:

                // No funciona de esta forma.
                Objeto o = lista_objetos.get(info.position);
                Intent i = new Intent(UsuarioRegistrado.this, NuevoObjeto.class);
                i.putExtra("emisor", o.getEmisor());
                i.putExtra("destinatario", o.getDestinatario());
                i.putExtra("urgencia", String.valueOf(o.getNivel_urgencia()));
                i.putExtra("nota", o.getTexto());
                //i.putExtra("EditarObjeto", o);
                post = info.position;
                startActivityForResult(i, CODE1);
                break;
            case R.id.opcBorrar:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setMessage(getResources().getString(R.string.mgsborrar));
                // Alt + Enter => Para los errores | Control + Espacio => Ayuda
                alerta.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        lista_objetos.remove(info.position);
                        //lista_objetos.remove(o);
                        adaptadorObjeto.notifyDataSetChanged();

                    }
                });
                AlertDialog dialogo = alerta.create();
                dialogo.show();
                break;

        }
        return true;
    }
    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btn_max:
                i = new Intent(UsuarioRegistrado.this,Lista.class);
                i.putExtra("emisor",txt_userR.getText());
                i.putExtra("lista", lista_objetos);
                startActivityForResult(i,CODE3);
                break;
            case R.id.btn_vaciar:
                lista_objetos.clear();
                adaptadorObjeto.notifyDataSetChanged();
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
            //lista_objetos.clear();
            lista_objetos = (ArrayList<Objeto>) data.getSerializableExtra("lista");
        }
        adaptadorObjeto.notifyDataSetChanged();
    }
    private ArrayList<Objeto> CargarLista() {
        ArrayList<Objeto> aux = new ArrayList<Objeto>();
        aux.add(new Objeto("Usuario", "A otro usuario", 3, "Este es un mensaje de gran importancia"));
        aux.add(new Objeto("Prueba", "Prueba", "ESTE ES EL TEXTO"));
        aux.add(new Objeto("Yo", "A mi mismo", 2, "Me aburro"));
        aux.add(new Objeto("Admin", "Sinnombre70", 1, "Tu cuenta va a ser baneada"));
        aux.add(new Objeto("Yo", "Profesor", "Con respecto a mi proyecto, un 11,¿no?"));
        aux.add(new Objeto("Profesor", "A mi", "Estás suspendido"));
        aux.add(new Objeto("Root", "A mi mismo", "No vale la pena"));
        aux.add(new Objeto("Jrublgo", "Prueba", 2, "Mensaje de poca importancia"));
        aux.add(new Objeto("Yo", "Yo", 2, "Hablando conmigo mismo"));
        aux.add(new Objeto("Elbuskador1", "A la gente de Erosekai", 1, "¿Os gusta o no? ;)"));
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
