package com.example.androidmaterial.clases;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidmaterial.BBDD.MetodosBBDD;
import com.example.androidmaterial.actividades.MainActivity;

public class Nadador {

    private String nombre;
    private int edad;
    private String nacionalidad;

    public Nadador(Nadador aux){
       this.nombre = aux.getNombre();
       this.nacionalidad= aux.getNacionalidad();
       this.edad=aux.getEdad();
    }
    public Nadador(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
