package com.example.androidmaterial.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidmaterial.BBDD.BBDDNadador;
import com.example.androidmaterial.BBDD.MetodosBBDD;
import com.example.androidmaterial.R;
import com.example.androidmaterial.clases.Adapter;
import com.example.androidmaterial.clases.Entrenamiento;
import com.example.androidmaterial.clases.Nadador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int CODIGO_ANHADIR = 100;
    private MetodosBBDD db;
    private ArrayList<Entrenamiento> listaEntrenamientos;
    private Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BBDDNadador dbn = new BBDDNadador(this);
        Nadador n = new Nadador();
        n.setNombre("Nombre");
        n.setNacionalidad("España");
        n.setEdad(00);
        dbn.setNadador(n);

        this.db = new MetodosBBDD(this.getApplicationContext());
        this.listaEntrenamientos = db.recuperar();

        if (adapter==null){
            adapter = new Adapter(this, listaEntrenamientos);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton button = (FloatingActionButton) this.findViewById(R.id.anhadir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anhadir = new Intent(MainActivity.this, AddActivity.class);
                anhadir.putExtra("id", -1);
                MainActivity.this.startActivityForResult(anhadir, CODIGO_ANHADIR);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean toret = false;

        switch (menuItem.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("id",-1);
                this.startActivityForResult(intent, CODIGO_ANHADIR);
                toret = true;
                break;
            case R.id.estadisticas:
                Intent subActividad = new Intent(MainActivity.this, Estadisticas.class);
                MainActivity.this.startActivity(subActividad);
                break;
            case R.id.eliminar:
                listaEntrenamientos = db.recuperar();
                String[] strings = new String[listaEntrenamientos.size()];
                final boolean[] selected = new boolean[listaEntrenamientos.size()];

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings[i] = listaEntrenamientos.get(i).toString();
                    selected[i]=false;
                }

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
                builder.setTitle("¿Qué entrenamientos desea eliminar?");
                builder.setMultiChoiceItems(strings, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] =  isChecked;
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int aux = 0;
                        for(int i = 0; i<listaEntrenamientos.size();i++){
                            if (selected[i]){
                                adapter.delete(null, (i-aux));
                                aux++;
                            }
                        }
                    }
                });
                builder.create().show();

                break;
            case R.id.modificar:
                listaEntrenamientos = db.recuperar();
                String[] strings2 = new String[listaEntrenamientos.size()];
                final Integer[] selected2 = new Integer[1];
                selected2[0]=0;

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings2[i] = listaEntrenamientos.get(i).toString();
                }

                MaterialAlertDialogBuilder builder2 = new MaterialAlertDialogBuilder(this);
                builder2.setTitle("¿Qué entrenamiento desea modificar?");
                builder2.setSingleChoiceItems(strings2, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected2[0] = which;
                    }
                });
                builder2.setNegativeButton("Cancelar", null);
                builder2.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        intent.putExtra("id", listaEntrenamientos.get(selected2[0]).getId());
                        MainActivity.this.startActivityForResult(intent, CODIGO_ANHADIR);
                    }
                });
                builder2.create().show();

                break;
            case R.id.configuracion:
                Intent subactividad = new Intent(MainActivity.this, Configuracion.class);
                MainActivity.this.startActivity(subactividad);
                break;
        }

        return toret;
    }

    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent retData) {
        super.onActivityResult(requestCode, resultCode, retData);

        if (requestCode==CODIGO_ANHADIR && resultCode==RESULT_OK){
            int id = retData.getIntExtra("id", -1);
            adapter.nuevaCard(db.getEntrenamiento(id));
        } else if (resultCode==RESULT_OK) {
            int id = retData.getIntExtra("id", -1);
            System.out.println("Este es el id que creo que falla: " + id);
            adapter.modificarCard(db.getEntrenamiento(id));
        }

    }

}
