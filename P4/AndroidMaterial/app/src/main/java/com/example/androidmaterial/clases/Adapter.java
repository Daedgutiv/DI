package com.example.androidmaterial.clases;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidmaterial.BBDD.MetodosBBDD;
import com.example.androidmaterial.R;
import com.example.androidmaterial.actividades.AddActivity;
import com.example.androidmaterial.actividades.MainActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public Context context;
    public ArrayList<Entrenamiento> lista;
    private MetodosBBDD db;

    public Adapter(Context c, ArrayList<Entrenamiento> lista){
        this.context=c;
        this.lista=lista;
        this.db = new MetodosBBDD(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String name = lista.get(position).getNombre();
        TextView nameTextView = holder.nombre;
        nameTextView.setText(name);
        String fecha = lista.get(position).getFecha();
        TextView fechaTV = holder.fecha;
        fechaTV.setText(fecha);
        String duracion = lista.get(position).getHoras() + " h " + lista.get(position).getMin() + " min " + lista.get(position).getSegundos() + " s";
        TextView duracionTV = holder.duracion;
        duracionTV.setText(duracion);
        String distancia = lista.get(position).getKm() + " km " + lista.get(position).getMetros() + " m";
        TextView distanciaTV = holder.distancia;
        distanciaTV.setText(distancia);
        String tipo = lista.get(position).getTipo();
        TextView tipoTV = holder.psicina;
        tipoTV.setText(tipo);
    }

    @Override
    public int getItemCount() {
        if (lista.isEmpty()){
            return 0;
        } else {
            return lista.size();
        }
    }

    public void deleteCard(View view, int list_position){
        delete(view, list_position);
    }

    public void delete(final View view, final int list_position){
        db.eliminar(lista.get(list_position).getId());
        lista.remove(list_position);
        notifyItemRemoved(list_position);
    }

    public void nuevaCard(Entrenamiento entrenamiento){
        lista.add(entrenamiento);
        ((MainActivity) context).doSmoothScroll(getItemCount());
        notifyItemInserted(getItemCount());
    }

    public void modificarCard(Entrenamiento entrenamiento){
        int pos=-1;
        for(int i=0;i<lista.size();i++){
            System.out.println(lista.get(i).getNombre() + ": " + lista.get(i).getId());
            if(lista.get(i).getId()==entrenamiento.getId()){
                pos=i;
            }
        }
        System.out.println("Posicion del entrenamiento = " + pos);
        lista.set(pos, entrenamiento);
        notifyItemChanged(pos);
    }

    @Override
    public long getItemId(int position){
        return lista.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

        private TextView nombre;
        private TextView fecha;
        private TextView duracion;
        private TextView distancia;
        private TextView psicina;
        private Button eliminar;

        public ViewHolder(View v){
            super(v);

            v.setOnCreateContextMenuListener(this);

            nombre = (TextView) v.findViewById(R.id.nombrecard);
            fecha = (TextView) v.findViewById(R.id.fechaCard);
            duracion = (TextView) v.findViewById(R.id.horasCard);
            distancia = (TextView) v.findViewById(R.id.kmCard);
            psicina = (TextView) v.findViewById(R.id.piscinaCard);
            eliminar = (Button) v.findViewById(R.id.eliminar);

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(itemView, getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int requestCode = getAdapterPosition();
                    String name = lista.get(requestCode).getNombre();
                    Intent subactividad = new Intent((AppCompatActivity) context, AddActivity.class);
                    subactividad.putExtra("id", lista.get(requestCode).getId());
                    ((AppCompatActivity) context).startActivityForResult(subactividad, requestCode);
                }
            });



        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){
                case 1:
                    delete(itemView, getAdapterPosition());
                    break;
                case 2:
                    int requestCode = getAdapterPosition();
                    Intent subactividad = new Intent((AppCompatActivity) context, AddActivity.class);
                    subactividad.putExtra("id", lista.get(requestCode).getId());
                    ((AppCompatActivity) context).startActivityForResult(subactividad, requestCode);
                    break;

            }
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem borrar = menu.add(Menu.NONE, 1, 1, "Borrar");
            borrar.setOnMenuItemClickListener(this);
            MenuItem modificar = menu.add(Menu.NONE, 2, 2,"Modificar");
            modificar.setOnMenuItemClickListener(this);
        }
    }
}
