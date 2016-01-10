package com.example.jesus1.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class NuevoObjeto extends AppCompatActivity implements View.OnClickListener, Serializable{

    TextView txt_useremisor;
    EditText etxt_destinatario;
    EditText etxt_nota;
    EditText etxt_mensaje;
    Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_objeto);

        txt_useremisor = (TextView) findViewById(R.id.txt_useremisor);
        txt_useremisor.setText(getIntent().getStringExtra("user"));
        etxt_destinatario = (EditText) findViewById(R.id.etxt_destinatario);
        etxt_nota = (EditText) findViewById(R.id.etxt_nota);
        etxt_mensaje = (EditText) findViewById(R.id.etxt_mensaje);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_enviar:
                if (etxt_destinatario.length() == 0 || Integer.parseInt(etxt_nota.getText()
                        .toString()) > 3 || etxt_mensaje.length() == 0 ) {
                    Toast.makeText(NuevoObjeto.this,"Campos Inválidos. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                    etxt_mensaje.setText("");
                    etxt_destinatario.setText("");
                    etxt_nota.setText("");
                }
                else {

                    Intent i = new Intent(NuevoObjeto.this,Lista.class);

                    if (etxt_nota.length() == 0) {

                        Objeto nuevo_objeto = new Objeto(txt_useremisor.getText().toString(),
                                etxt_destinatario.getText().toString(),
                                etxt_mensaje.getText().toString());
                                i.putExtra("NuevoObjeto", (Serializable) nuevo_objeto);
                                startActivityForResult(i,1);
                    }
                    else {

                        Objeto nuevo_objeto = new Objeto(txt_useremisor.getText().toString(),
                                etxt_destinatario.getText().toString(),
                                Integer.parseInt(etxt_nota.getText().toString()),
                                etxt_mensaje.getText().toString());
                                i.putExtra("NuevoObjeto", (Serializable) nuevo_objeto);
                                startActivityForResult(i,1);
                    }

                }


        }
    }
}
