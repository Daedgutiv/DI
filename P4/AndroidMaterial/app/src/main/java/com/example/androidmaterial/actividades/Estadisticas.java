package com.example.androidmaterial.actividades;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmaterial.BBDD.BBDDNadador;
import com.example.androidmaterial.R;
import com.example.androidmaterial.clases.AdapterEstadisticas;
import com.example.androidmaterial.clases.Nadador;

public class Estadisticas extends AppCompatActivity {

    private BBDDNadador db;
    private Nadador n;
    private AdapterEstadisticas adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.estadisticas_layout);

        this.db= new BBDDNadador(this.getApplicationContext());
        this.n=db.recuperarNadador();

        if (adapter==null){
            adapter= new AdapterEstadisticas(this);
        }

        recyclerView = (RecyclerView) findViewById(R.id.adapterEstadisticas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
