package com.example.jesus1.registro;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista extends AppCompatActivity implements Serializable {

    private ListView vista_objetos;
    private ArrayList<Objeto> lista_objetos = new ArrayList<Objeto>();
    private final int CODE1 = 1;
    private final int CODE2 = 2;
    private int post;
    private String usuReg;
    private boolean vista_btnmostrar = true;

    // Necesario para la clase AdaptadorObjeto
    private AdaptadorObjeto adaptadorObjeto;
    // Para el inflate. Es una inner class. Extiende de ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Intent i = getIntent();
        lista_objetos = (ArrayList<Objeto>) i.getSerializableExtra("lista");
        usuReg = i.getStringExtra("emisor");
        vista_objetos = (ListView) findViewById(R.id.vista_objetos);
        // Se asemeja a la construccion de la clase AdaptadorObjeto
        adaptadorObjeto = new AdaptadorObjeto(this);
      vista_objetos.setAdapter(adaptadorObjeto);
        registerForContextMenu(vista_objetos);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;

        switch(item.getItemId()) {
            case R.id.action_add:
                i = new Intent(Lista.this,NuevoObjeto.class);
                i.putExtra("emisor",usuReg);
                startActivityForResult(i, CODE2);
                break;
            case R.id.action_contact:
                i = new Intent(Lista.this,Contacto.class);
                startActivity(i);
                break;
            case R.id.action_exit:
                finish();
                break;
            case R.id.action_see:
                if (!vista_btnmostrar) {

                    vista_objetos.setVisibility(View.VISIBLE);
                    vista_btnmostrar = true;

                }
                else if (vista_btnmostrar)
                {

                    vista_objetos.setVisibility(View.INVISIBLE);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()) {

            case R.id.opcEditar:

                // No funciona de esta forma.
                Objeto o = lista_objetos.get(info.position);
                Intent i = new Intent(Lista.this, NuevoObjeto.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
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
            Toast.makeText(Lista.this, R.string.msgadd, Toast.LENGTH_SHORT).show();
            //ista_objetos.add((Objeto) data.getSerializableExtra("NuevoObjeto"));
        }
        else if (resultCode == RESULT_CANCELED) {

        }
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
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Lista.this,UsuarioRegistrado.class);
        i.putExtra("lista",lista_objetos);
        setResult(RESULT_CANCELED,i);
        super.onBackPressed();

    }
}

