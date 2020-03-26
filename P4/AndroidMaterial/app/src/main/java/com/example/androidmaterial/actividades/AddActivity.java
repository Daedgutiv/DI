package com.example.androidmaterial.actividades;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidmaterial.BBDD.MetodosBBDD;
import com.example.androidmaterial.R;
import com.example.androidmaterial.clases.Adapter;
import com.example.androidmaterial.clases.Entrenamiento;
import com.google.android.material.textfield.TextInputEditText;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements SlideDatePickerDialogCallback {

    private Button fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_layout);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button anhadir = (Button) this.findViewById(R.id.btanhadir);
        Button cancelar = (Button) this.findViewById(R.id.btcancelar);

        fecha = (Button) this.findViewById(R.id.btfecha);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar date= Calendar.getInstance();
                date.set(Calendar.YEAR, 2030);

                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(date);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(), "Dialog");
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.setResult(Activity.RESULT_CANCELED);
                AddActivity.this.finish();
            }
        });

        final TextInputEditText nombre = (TextInputEditText) this.findViewById(R.id.nombre2);
        final TextInputEditText horas = (TextInputEditText) this.findViewById(R.id.horas2);
        final TextInputEditText min = (TextInputEditText) this.findViewById(R.id.min2);
        final TextInputEditText segundos = (TextInputEditText) this.findViewById(R.id.segundos2);
        final TextInputEditText km = (TextInputEditText) this.findViewById(R.id.km2);
        final TextInputEditText metros = (TextInputEditText) this.findViewById(R.id.metros2);

        final Intent datosEnviados = this.getIntent();
        final int id = datosEnviados.getExtras().getInt("id");

        System.out.println(id);
        if (id!=-1){
            MetodosBBDD db = new MetodosBBDD(this.getApplicationContext());

            Entrenamiento aux = db.getEntrenamiento(id);
            anhadir.setText("Modificar");
            nombre.setText(aux.getNombre());
            horas.setText(String.valueOf(aux.getHoras()));
            min.setText(String.valueOf(aux.getMin()));
            segundos.setText(String.valueOf(aux.getSegundos()));
            km.setText(String.valueOf(aux.getKm()));
            metros.setText(String.valueOf(aux.getMetros()));
            fecha.setText(aux.getFecha());

            if (aux.getTipo().equalsIgnoreCase("Piscina Pequeña")){
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.first);
                radioButton.setChecked(true);
            } else if (aux.getTipo().equalsIgnoreCase("Piscina Mediana")){
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.second);
                radioButton.setChecked(true);
            } else {
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.third);
                radioButton.setChecked(true);
            }

        } else {
            anhadir.setText("Añadir");
        }

        metros.addTextChangedListener(new TextWatcher() {
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

        km.addTextChangedListener(new TextWatcher() {
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

        segundos.addTextChangedListener(new TextWatcher() {
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
        min.addTextChangedListener(new TextWatcher() {
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

        horas.addTextChangedListener(new TextWatcher() {
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

        RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.tipoPiscina);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                comprobar();
            }
        });

        anhadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entrenamiento entrenamiento = new Entrenamiento();
                entrenamiento.setId(id);
                entrenamiento.setNombre(nombre.getText().toString());
                entrenamiento.setFecha(fecha.getText().toString());
                entrenamiento.setHoras(Integer.valueOf(horas.getText().toString()));
                entrenamiento.setMin(Integer.valueOf(min.getText().toString()));
                entrenamiento.setSegundos(Integer.valueOf(segundos.getText().toString()));
                entrenamiento.setKm(Integer.valueOf(km.getText().toString()));
                entrenamiento.setMetros(Integer.valueOf(metros.getText().toString()));
                RadioGroup radio = (RadioGroup) AddActivity.this.findViewById(R.id.tipoPiscina);
                RadioButton radioButton = (RadioButton) AddActivity.this.findViewById(radio.getCheckedRadioButtonId());
                entrenamiento.setTipo(radioButton.getText().toString());

                MetodosBBDD db = new MetodosBBDD(AddActivity.this.getApplicationContext());
                Intent retData = new Intent();
                if (id==-1){
                    db.anhadir(entrenamiento);
                    retData.putExtra("id", db.recuperar().get(db.recuperar().size()-1).getId());
                } else {
                    db.modificar(entrenamiento);
                    retData.putExtra("id", id);
                }

                db.close();


                AddActivity.this.setResult(Activity.RESULT_OK, retData);
                AddActivity.this.finish();
            }
        });

    }

    private void comprobar(){
        final TextInputEditText nombre = (TextInputEditText) this.findViewById(R.id.nombre2);
        final TextInputEditText horas = (TextInputEditText) this.findViewById(R.id.horas2);
        final TextInputEditText min = (TextInputEditText) this.findViewById(R.id.min2);
        final TextInputEditText segundos = (TextInputEditText) this.findViewById(R.id.segundos2);
        final TextInputEditText km = (TextInputEditText) this.findViewById(R.id.km2);
        final TextInputEditText metros = (TextInputEditText) this.findViewById(R.id.metros2);
        Button anhadir = (Button) this.findViewById(R.id.btanhadir);
        if (!nombre.getText().toString().trim().isEmpty() && !horas.getText().toString().trim().isEmpty() && !min.getText().toString().trim().isEmpty() &&
                !segundos.getText().toString().trim().isEmpty() && !km.getText().toString().trim().isEmpty() && !metros.getText().toString().trim().isEmpty()
                && !fecha.getText().toString().trim().equalsIgnoreCase("Seleccionar Fecha")){


            anhadir.setEnabled(true);
        } else {
            anhadir.setEnabled(false);
        }
    }

    @Override
    public void onPositiveClick(int i, int i1, int i2, Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha.setText(dateFormat.format(calendar.getTime()));
        comprobar();
    }

}
