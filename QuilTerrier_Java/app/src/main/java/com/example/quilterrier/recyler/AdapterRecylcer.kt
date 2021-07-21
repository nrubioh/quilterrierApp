package com.example.quilterrier.recyler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.quilterrier.recyler.AdapterRecylcer.MyViewHolder
import android.widget.TextView
import com.example.quilterrier.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

class AdapterRecylcer(
    var context: Context,
    var name: Array<String>,
    var type: Array<String>,
    var location: Array<String>,
    var especieAnimal: Array<String>,
    var images: IntArray
) : RecyclerView.Adapter<MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var tipo: TextView
        var ubicacion: TextView
        var imageView_animal: ImageView
        var especie: ImageView

        init {
            imageView_animal = itemView.findViewById(R.id.imageView_animal)
            nombre = itemView.findViewById(R.id.textView_nombre)
            tipo = itemView.findViewById(R.id.textView_tipo)
            ubicacion = itemView.findViewById(R.id.textView_ubicacion)
            especie = itemView.findViewById(R.id.especie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item, parent, false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nombre.text = name[position]
        holder.tipo.text = type[position]
        cambiarColorcitoDeNico(holder, position)
        cambiarImagencitaDeNico(holder, position)

        //holder.tipo.setBackgroundColor(context.getResources().getColor(R.color.amarillo_patito));
        holder.ubicacion.text = location[position]
        holder.imageView_animal.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun cambiarColorcitoDeNico(holder: MyViewHolder, position: Int) {
        if (type[position] == "Perdido!") {
            holder.tipo.setBackgroundColor(context.resources.getColor(R.color.amarillo_patito))
        } else {
            holder.tipo.setBackgroundColor(context.resources.getColor(R.color.rosado_profesor))
        }
    }

    fun cambiarImagencitaDeNico(holder: MyViewHolder, position: Int) {
        if (especieAnimal[position] == "Gato") {
            holder.especie.setImageResource(R.drawable.yarnball)
        } else {
            holder.especie.setImageResource(R.drawable.pata)
        }
    }
}