package com.example.androidmaterial.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.androidmaterial.clases.Adapter;
import com.example.androidmaterial.clases.Entrenamiento;
import com.example.androidmaterial.clases.Nadador;

import java.util.ArrayList;

public class MetodosBBDD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "app_nadador";
    private static final int VERSION_BD = 1;
    private static final String NOMBRE_TABLA = "entrenamientos";
    private static final String ID = "id";
    private static final String NOMBRE ="nombre";
    private static final String FECHA = "fecha";
    private static final String HORAS="horas";
    private static final String MIN="min";
    private static final String SEGUNDOS="segundos";
    private static final String KM="km";
    private static final String METROS="metros";
    private static final String TIPO="tipo";



    public MetodosBBDD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBManager", "Creando BBDD " + NOMBRE_BD + " v" + VERSION_BD);

        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + NOMBRE + " string(30) NOT NULL ,"
            + FECHA + " string(10) NOT NULL ," + HORAS + " int," + MIN + " int," + SEGUNDOS + " int," + KM + " int," + METROS+ " int," + TIPO + " string(30) NOT NULL) ");

            db.setTransactionSuccessful();

        } catch (SQLException exc){
            Log.e("DBManager.onCreate", exc.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager", "DB: " + NOMBRE_BD + ": v" + oldVersion + " -> v" + newVersion);

        try{
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onUpgrade", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        this.onCreate(db);

    }



    public ArrayList<Entrenamiento> recuperar(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA, null);
        ArrayList<Entrenamiento> lista = new ArrayList<Entrenamiento>();

        if(cursor!=null){
            while(cursor.moveToNext()){
                Entrenamiento entrenamiento = new Entrenamiento(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8));
                lista.add(entrenamiento);
            }
        }
        return lista;
    }

    public boolean anhadir(Entrenamiento entrenamiento){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean toret = false;
        try{
            db.beginTransaction();
            db.execSQL("INSERT OR IGNORE INTO " + NOMBRE_TABLA + "(" +NOMBRE + ", " + FECHA + ", " + HORAS + ", " + MIN + ", " + SEGUNDOS + ", " + KM + ", " + METROS + ", " + TIPO +
                    ") VALUES(?,?,?,?,?,?,?,?)", new String[]{entrenamiento.getNombre(), entrenamiento.getFecha(), String.valueOf(entrenamiento.getHoras()), String.valueOf(entrenamiento.getMin()),
                    String.valueOf(entrenamiento.getSegundos()), String.valueOf(entrenamiento.getKm()), String.valueOf(entrenamiento.getMetros()), entrenamiento.getTipo()});
            db.setTransactionSuccessful();
            toret=true;
        }catch (SQLException exc){
            Log.e("DBManager.insertar", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        db.close();
        return toret;
    }

    public boolean eliminar (int id){
        boolean toret = false;

        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.beginTransaction();
            db.execSQL("DELETE FROM " + NOMBRE_TABLA + " WHERE " + ID + " = ?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            toret=true;
        } catch (SQLException exc){
            Log.e("DBManager.eliminar", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        return toret;
    }

    public boolean modificar(Entrenamiento entrenamiento){
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(NOMBRE, entrenamiento.getNombre());
        valores.put(FECHA, entrenamiento.getFecha());
        valores.put(HORAS, entrenamiento.getHoras());
        valores.put(MIN, entrenamiento.getMin());
        valores.put(SEGUNDOS, entrenamiento.getSegundos());
        valores.put(KM, entrenamiento.getKm());
        valores.put(METROS, entrenamiento.getMetros());
        valores.put(TIPO, entrenamiento.getTipo());

        try {
            db.beginTransaction();
            db.update(NOMBRE_TABLA, valores, ID + "=?", new String[]{Integer.toString(entrenamiento.getId())});
            db.setTransactionSuccessful();
            toret=true;
        } catch (SQLException exc){
            Log.e("DBManager.modificar", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        db.close();

        return toret;
    }

    public Entrenamiento getEntrenamiento(int id){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + NOMBRE + ", " + FECHA + ", " + HORAS + ", " + MIN + ", " + SEGUNDOS + ", " + KM + ", " + METROS + ", " + TIPO + ", " + ID + " FROM " + NOMBRE_TABLA + " WHERE " + ID + " = " + id, null );

        Entrenamiento entrenamiento=null;

        while(cursor.moveToNext()){
            entrenamiento = new Entrenamiento(cursor.getInt(8),cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7));
        }

        cursor.close();

        return entrenamiento;
    }

}
