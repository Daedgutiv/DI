package com.example.androidmaterial.actividades;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidmaterial.BBDD.BBDDNadador;
import com.example.androidmaterial.R;
import com.example.androidmaterial.clases.Nadador;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class Configuracion extends AppCompatActivity {

    private Nadador nadador;
    private BBDDNadador db;
    private Locale locale;
    private Configuration configuracion;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.configuracion_activity);

        this.configuracion= new Configuration();

        this.db = new BBDDNadador(this.getApplicationContext());
        this.nadador = db.recuperarNadador();

        Button btModificarNadador = (Button) this.findViewById(R.id.cambiarNadador);
        Button btCancelar = (Button) this.findViewById(R.id.btcancelarCOnfi);

        final TextInputEditText nombre = (TextInputEditText) this.findViewById(R.id.nombreConfi2);
        final TextInputEditText edad = (TextInputEditText) this.findViewById(R.id.edadConfi2);
        final TextInputEditText nacionalidad = (TextInputEditText) this.findViewById(R.id.nacionalidadConfi2);
        final int[] aux = {0};


        RadioGroup radio = (RadioGroup) Configuracion.this.findViewById(R.id.leguaje);
        RadioButton radioButton = (RadioButton) Configuracion.this.findViewById(radio.getCheckedRadioButtonId());

        nombre.setText(nadador.getNombre());
        edad.setText(String.valueOf(nadador.getEdad()));
        nacionalidad.setText(nadador.getNacionalidad());

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuracion.this.finish();
            }
        });

        btModificarNadador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nadador aux3 = new Nadador();
                aux3.setNombre(nombre.getText().toString());
                aux3.setNacionalidad(nacionalidad.getText().toString());
                aux3.setEdad(Integer.valueOf(edad.getText().toString()));

                db.cambiarNadador(aux3);

                db.close();

                switch (aux[0]){
                    case 0:
                        locale= new Locale("en");
                        configuracion.locale=locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        configuracion.locale=locale;
                        break;
                }

                getResources().updateConfiguration(configuracion, null);
                Intent refresh = new Intent(Configuracion.this, MainActivity.class);
                startActivity(refresh);
                Configuracion.this.finish();
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                comprobar();
                if (aux[0] ==1){
                    aux[0] =0;
                } else {
                    aux[0] =1;
                }
            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobar();
            }
        });

        edad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobar();
            }
        });

        nacionalidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobar();
            }
        });

    }

    public void comprobar(){
        final TextInputEditText nombre = (TextInputEditText) this.findViewById(R.id.nombreConfi2);
        final TextInputEditText edad = (TextInputEditText) this.findViewById(R.id.edadConfi2);
        final TextInputEditText nacionalidad = (TextInputEditText) this.findViewById(R.id.nacionalidadConfi2);
        Button btModificarNadador = (Button) this.findViewById(R.id.cambiarNadador);

        if (!nombre.getText().toString().trim().isEmpty() && !edad.getText().toString().trim().isEmpty() && !nacionalidad.getText().toString().trim().isEmpty()){
            btModificarNadador.setEnabled(true);
        } else {
            btModificarNadador.setEnabled(false);
        }

    }


}
