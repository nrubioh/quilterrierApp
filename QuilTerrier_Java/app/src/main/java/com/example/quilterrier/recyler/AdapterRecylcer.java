package com.example.quilterrier.recyler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quilterrier.R;



public  class AdapterRecylcer extends RecyclerView.Adapter<AdapterRecylcer.MyViewHolder> {
     String name[], type[], location[], especieAnimal[];
    int images[];
    Context context;



    public AdapterRecylcer(Context ctx, String nombre[], String tipo[],String ubicacion[], String especieArr[],
                           int img[]){
        context = ctx;
        name = nombre;
        type = tipo;
        location = ubicacion;
        images = img;
        especieAnimal = especieArr;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, tipo, ubicacion;
        ImageView imageView_animal,especie;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            imageView_animal = itemView.findViewById(R.id.imageView_animal);
            nombre = itemView.findViewById(R.id.textView_nombre);
            tipo = itemView.findViewById(R.id.textView_tipo);
            ubicacion = itemView.findViewById(R.id.textView_ubicacion);
            especie = itemView.findViewById(R.id.especie);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nombre.setText(name[position]);
        holder.tipo.setText(type[position]);
        cambiarColorcitoDeNico(holder, position);
        cambiarImagencitaDeNico(holder, position);

        //holder.tipo.setBackgroundColor(context.getResources().getColor(R.color.amarillo_patito));
        holder.ubicacion.setText(location[position]);
        holder.imageView_animal.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void cambiarColorcitoDeNico(MyViewHolder holder, int position){
        if (type[position].equals("Perdido!")){
           holder.tipo.setBackgroundColor(context.getResources().getColor(R.color.amarillo_patito));
        }else{
            holder.tipo.setBackgroundColor(context.getResources().getColor(R.color.rosado_profesor));
        }
    }

    public void cambiarImagencitaDeNico(MyViewHolder holder, int position){
        if (especieAnimal[position].equals("Gato")){
            holder.especie.setImageResource(R.drawable.yarnball);
        }else{
            holder.especie.setImageResource(R.drawable.pata);
        }
    }

}



