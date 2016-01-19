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

public class NuevoObjeto extends AppCompatActivity implements View.OnClickListener, Serializable {

    TextView txt_useremisor;
    EditText etxt_destinatario;
    EditText etxt_nota;
    EditText etxt_mensaje;
    Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_objeto);
        // Este es el intent del main (startActivityForResult())
        Intent intent = getIntent();

        txt_useremisor = (TextView) findViewById(R.id.txt_useremisor);
        //txt_useremisor.setText(getIntent().getStringExtra("user"));
        txt_useremisor.setText(intent.getStringExtra("emisor"));

        etxt_destinatario = (EditText) findViewById(R.id.etxt_destinatario);
        etxt_destinatario.setText(intent.getStringExtra("destinatario"));

        etxt_nota = (EditText) findViewById(R.id.etxt_nota);
        etxt_nota.setText((intent.getStringExtra("urgencia")));

        etxt_mensaje = (EditText) findViewById(R.id.etxt_mensaje);
        etxt_mensaje.setText(intent.getStringExtra("nota"));

        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enviar:

                try {

                    if (etxt_destinatario.length() == 0 || Integer.parseInt(etxt_nota.getText()
                            .toString()) > 3 || etxt_mensaje.length() == 0) {
                        Toast.makeText(NuevoObjeto.this, R.string.msgerror, Toast.LENGTH_SHORT).show();
                    }
                }
                    catch (java.lang.NumberFormatException e) {
                       // Toast.makeText(NuevoObjeto.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        etxt_nota.setText("0");
                    }
                finally {
                    // AQUI LO CONVIERTE
                    Intent i = new Intent();
                    i.putExtra("emisor", txt_useremisor.getText().toString());
                    i.putExtra("destinatario", etxt_destinatario.getText().toString());
                    i.putExtra("nota", Integer.parseInt(etxt_nota.getText().toString()));
                    i.putExtra("mensaje", etxt_mensaje.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                    break;
                    // FORMA QUE NO FUNCIONA
                        /*Objeto nuevo_objeto = new Objeto(txt_useremisor.getText().toString(),
                                etxt_destinatario.getText().toString(),
                                Integer.parseInt(etxt_nota.getText().toString()),
                                etxt_mensaje.getText().toString());
                                i.putExtra("NuevoObjeto", nuevo_objeto);*/
                    //startActivityForResult(i,1);
                }
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(NuevoObjeto.this, R.string.msgexit, Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        super.onBackPressed();
        }
    }

