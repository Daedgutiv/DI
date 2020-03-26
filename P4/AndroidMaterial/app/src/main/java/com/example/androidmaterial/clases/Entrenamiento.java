package com.example.androidmaterial.clases;

import java.util.Date;

public class Entrenamiento {

    private int id;
    private String nombre;
    private String fecha;
    private int horas;
    private int min;
    private int segundos;
    private int km;
    private int metros;
    private String tipo; //piscina grande, mediana o pequeña

    public Entrenamiento(){

    }

    public Entrenamiento(int id, String nombre, String fecha, int horas, int min, int segundos, int km, int metros, String tipo){
        this.id =id;
        this.nombre=nombre;
        this.fecha=fecha;
        this.horas= horas;
        this.min=min;
        this.segundos= segundos;
        this.km=km;
        this.metros=metros;
        this.tipo=tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getDistanciaKM(){
        return  km+(metros/1000);
    }
    public float getDuracionH(){
        return horas+(min/60);
    }

    @Override
    public String toString() {
        return "Entrenamiento: " + nombre + " Fecha: " + fecha;
    }
}
