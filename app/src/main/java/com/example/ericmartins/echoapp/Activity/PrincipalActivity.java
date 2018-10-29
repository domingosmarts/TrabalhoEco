package com.example.ericmartins.echoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ericmartins.echoapp.R;


public class PrincipalActivity extends AppCompatActivity {
    private Button btnMural;
    private Button btnClassif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnMural = (Button) findViewById(R.id.btnMural);
        btnClassif = (Button) findViewById(R.id.btnClassif);

        btnMural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMural();
            }
        });
        btnClassif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirClassif();
            }
        });
    }
    public void abrirMural(){
        Intent abrirMural = new Intent (PrincipalActivity.this, MuralActivity.class);
        startActivity(abrirMural);
    }
    public void abrirClassif(){
        Intent abrirClassif = new Intent (PrincipalActivity.this, ClassificacaoActivity.class);
        startActivity(abrirClassif);
    }
}
