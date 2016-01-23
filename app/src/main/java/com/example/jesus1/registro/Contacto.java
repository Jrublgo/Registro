package com.example.jesus1.registro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contacto extends AppCompatActivity implements View.OnClickListener {

    Button btn_llamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        btn_llamar = (Button) findViewById(R.id.btn_llamar);
        btn_llamar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_llamar:
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+34671330820"));
                startActivity(i);

        }
    }
}
