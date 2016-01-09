package com.example.jesus1.registro;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    private ListView vista_objetos;

    ArrayAdapter<Objeto> adapterListaObjetos;

    ArrayList<Objeto> lista_objetos = new ArrayList<Objeto>();
    // Necesario para la clase AdaptadorObjeto
    private AdaptadorObjeto adaptadorObjeto;
    // Para el inflate. Es una inner class. Extiende de ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista_objetos = CargarLista();


        vista_objetos = (ListView) findViewById(R.id.vista_objetos);
        // Se asemeja a la construccion de la clase AdaptadorObjeto
        adapterListaObjetos = new ArrayAdapter<Objeto>(this,R.layout.support_simple_spinner_dropdown_item,lista_objetos);
        vista_objetos.setAdapter(adapterListaObjetos);

        registerForContextMenu(vista_objetos);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones_lista, menu);
    }

    private ArrayList<Objeto> CargarLista() {
        ArrayList<Objeto> aux = new ArrayList<Objeto>();
        aux.add(new Objeto("Jrublgo","Javier Ruano",3,"Este es el proyecto de Android"));
        aux.add(new Objeto("Prueba","Prueba","ESTE ES EL TEXTO"));
        aux.add(new Objeto("Yo","A mi mismo",2,"Acabar este proyecto"));
        aux.add(new Objeto("Yo","Jrublgo",1,"Mensaje a otro usuario"));
        aux.add(new Objeto("Yo","Javier Ruano","Quisiera resivar mi examen"));
        aux.add(new Objeto("Root","Prueba","Me estoy quedando sin ideas"));
        aux.add(new Objeto("Admin","yo",3,"Estás suspendido"));
        aux.add(new Objeto("Root", "A mi mismo", "No vale la pena"));
        aux.add(new Objeto("Profesor","Alumno","Tienes un 0"));
        aux.add(new Objeto("Jrublgo","Prubea",2,"Mensaje de poca importancia"));
        aux.add(new Objeto("Profesor", "Yo", 2, "Hablando conmigo mismo"));
        aux.add(new Objeto("Alumno", "Una persona", 1, "ESTE ES EL TEXTO"));
        return  aux;
    }

    private class AdaptadorObjeto extends ArrayAdapter {

        Activity actividad;

        public AdaptadorObjeto(Activity actividad) {
            // Constructor con solo el contexto. Confunde el context context con el activity context
            super(actividad,R.layout.fila_objetos,lista_objetos);
            // En el super le pasamos la actividad, el layout que vamos a inflar y mostrar en
            // el ListView y la lista de objetos que está ligada al ListView a través del adaptador
            this.actividad = actividad;
        }
        // Obtener vista
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           // Borramos el super de este

            View item = convertView;
            // Se puede usar directamente el convertView

            ViewHolder holder;
            // Documentar

            if (item == null) {
                LayoutInflater inflater = actividad.getLayoutInflater();

                item = inflater.inflate(R.layout.fila_objetos, null);
                // Lo que se va a inflar

                holder = new ViewHolder();

                holder.txt_Destinatario = (TextView) item.findViewById(R.id.txt_destinatario);
                holder.txt_Emisor = (TextView) item.findViewById(R.id.txt_emisor);
                holder.txt_urgencia = (TextView) item.findViewById(R.id.txt_grado);
                holder.txt_nota = (TextView) item.findViewById(R.id.txt_nota);

                item.setTag(holder);
            } else {
                holder = (ViewHolder) item.getTag();
            }

            holder.txt_Destinatario.setText(lista_objetos.get(position).getDestinatario());
            holder.txt_Emisor.setText(lista_objetos.get(position).getEmisor());
            holder.txt_urgencia.setText(lista_objetos.get(position).getNivel_urgencia());
            holder.txt_nota.setText(lista_objetos.get(position).getTexto());

            return item;
        }
    }

    static class ViewHolder {
        private TextView txt_Emisor;
        private TextView txt_Destinatario;
        private TextView txt_urgencia;
        private TextView txt_nota;


    }
        }

