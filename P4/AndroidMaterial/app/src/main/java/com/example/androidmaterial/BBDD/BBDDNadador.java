package com.example.androidmaterial.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.androidmaterial.clases.Nadador;

public class BBDDNadador extends SQLiteOpenHelper {

    private static final String TABLA_NADADOR = "nadador";
    private static final String ID_NADADOR = "id";
    private static final String NOMBRE_NADADOR = "nombre";
    private static final String NACIONALIDAD = "nacionalidad";
    private static final String EDAD_NADADOR = "edad";

    public BBDDNadador( Context context) {
        super(context, TABLA_NADADOR, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_NADADOR + "(" + ID_NADADOR + " INTEGER PRIMARY KEY," + NOMBRE_NADADOR + " string(30) NOT NULL, " + NACIONALIDAD + " string(20) NOT NULL, " + EDAD_NADADOR + " int)");
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onCreateNadador", exc.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_NADADOR);
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onUpgrade", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        this.onCreate(db);
    }

    public Nadador recuperarNadador(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_NADADOR, null);
        Nadador n=null;

        while(cursor.moveToNext()){
            n = new Nadador();
            n.setNombre(cursor.getString(1));
            n.setEdad(cursor.getInt(3));
            n.setNacionalidad(cursor.getString(2));
        }


        return n;
    }

    public void setNadador(Nadador nadador){
        SQLiteDatabase db = this.getWritableDatabase();
        String id = "0";
        try {
            db.beginTransaction();
            db.execSQL("INSERT OR IGNORE INTO " + TABLA_NADADOR + "(" + ID_NADADOR + ", " + NOMBRE_NADADOR + ", " + NACIONALIDAD+ ", " + EDAD_NADADOR + ") VALUES(?,?,?,?)",
                    new String[]{id, nadador.getNombre(), nadador.getNacionalidad(), String.valueOf(nadador.getEdad())});
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.insertarNadador", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        db.close();
    }

    public void cambiarNadador(Nadador n){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(NOMBRE_NADADOR, n.getNombre());
        valores.put(NACIONALIDAD, n.getNacionalidad());
        valores.put(EDAD_NADADOR, n.getEdad());

        try {
            db.beginTransaction();
            db.update(TABLA_NADADOR, valores, ID_NADADOR + "=?", new String[]{"0"});
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.modificar", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        db.close();

    }
}
