package com.example.androidmaterial.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmaterial.BBDD.BBDDNadador;
import com.example.androidmaterial.BBDD.MetodosBBDD;
import com.example.androidmaterial.R;

import java.util.ArrayList;

public class AdapterEstadisticas extends RecyclerView.Adapter<AdapterEstadisticas.ViewHolder> {

    public Context context;
    public Nadador nadador;
    private BBDDNadador db;
    private MetodosBBDD db2;

    public AdapterEstadisticas (Context c){
        this.context=c;
        this.db = new BBDDNadador(context);
        this.db2 = new MetodosBBDD(context);
        this.nadador= db.recuperarNadador();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_estadisticas, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView nombre = holder.nombreNadador;
        nombre.setText(nadador.getNombre());
        TextView edad = holder.edadNadador;
        edad.setText(String.valueOf(nadador.getEdad()));
        TextView nacionalidad = holder.nacionalidadNadador;
        nacionalidad.setText(nadador.getNacionalidad());

        ArrayList<Entrenamiento> lista = db2.recuperar();

        TextView totales = holder.numeroEntrenamientos;
        totales.setText(String.valueOf(lista.size()));

        TextView distanciaTotal = holder.distanciaTotal;
        float auxDistancia= 0;
        float auxDuracion=0;
        int auxkm=0;
        int auxmetros=0;
        for (int i = 0;i<lista.size();i++){
            auxDuracion = auxDuracion + lista.get(i).getDuracionH();
            auxDistancia= auxDistancia + lista.get(i).getDistanciaKM();
            auxkm=auxkm+lista.get(i).getKm();
            auxmetros=auxmetros+lista.get(i).getMetros();
        }
        distanciaTotal.setText(String.valueOf(auxkm) + " km " + String.valueOf(auxmetros) + " m");

        TextView duracionTotal = holder.duracionTotal;
        int auxhoras=0;
        int auxmin=0;
        int auxsegundos=0;
        for (int i=0;i<lista.size();i++){
            auxhoras=auxhoras+lista.get(i).getHoras();
            auxmin =auxmin + lista.get(i).getMin();
            auxsegundos=auxsegundos+lista.get(i).getSegundos();
        }
        duracionTotal.setText(String.valueOf(auxhoras) + " h " + String.valueOf(auxmin) + " min " + String.valueOf(auxsegundos) + " s");

        TextView velocidad = holder.velocidadMedia;
        velocidad.setText(String.valueOf((auxDistancia/auxDuracion)));



    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreNadador;
        private TextView edadNadador;
        private TextView nacionalidadNadador;
        private  TextView distanciaTotal;
        private TextView duracionTotal;
        private TextView velocidadMedia;
        private TextView numeroEntrenamientos;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreNadador = (TextView) itemView.findViewById(R.id.nombreNadador);
            edadNadador = (TextView) itemView.findViewById(R.id.edadcard);
            nacionalidadNadador = (TextView) itemView.findViewById(R.id.nacionalidad);
            distanciaTotal = (TextView) itemView.findViewById(R.id.distanciaTotal);
            duracionTotal = (TextView) itemView.findViewById(R.id.tiempoTotal);
            velocidadMedia = (TextView) itemView.findViewById(R.id.velocidadMedia);
            numeroEntrenamientos = (TextView) itemView.findViewById(R.id.totales);
        }
    }
}
