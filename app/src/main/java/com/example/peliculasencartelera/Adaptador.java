package com.example.peliculasencartelera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter implements View.OnClickListener {

    ArrayList<Pelicula> datos;
    Context context;

    public Adaptador() {
    }

    public Adaptador(ArrayList<Pelicula> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recycle_item,parent,false);
        Holder holder = new Holder(view,context);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).binHolder(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}

class Holder extends RecyclerView.ViewHolder{

    TextView titulo;
    TextView descripcion;
    ImageView imagen;
    LinearLayout contenedor;
    Context context;
    Pelicula pelicula;


    public Holder(@NonNull View itemView,Context context) {
        super(itemView);
        titulo = itemView.findViewById(R.id.textTitulo);
        descripcion = itemView.findViewById(R.id.textDescripcion);
        imagen = itemView.findViewById(R.id.ivImagenPeli);
        contenedor = itemView.findViewById(R.id.contenedorItem);
        this.context = context;
    }

    public  void  binHolder(Pelicula pelicula){
        this.pelicula = pelicula;
        titulo.setText(pelicula.getTitulo());
        descripcion.setText(pelicula.getDescripcion());
        imagen.setImageBitmap(pelicula.imagen);
    }
}
