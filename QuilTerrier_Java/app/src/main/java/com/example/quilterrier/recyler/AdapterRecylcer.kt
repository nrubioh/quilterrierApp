package com.example.quilterrier.recyler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quilterrier.AnotherImage
import com.example.quilterrier.R
import com.example.quilterrier.recyler.AdapterRecylcer.MyViewHolder


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
        //holder.tipo.setBackgroundColor(context.getResources().getColor(R.color.amarillo_patito));
        holder.nombre.text = name[position]
        holder.tipo.text = type[position]
        holder.ubicacion.text = location[position]
        holder.imageView_animal.setImageResource(images[position])

        cambiarColorcitoDeNico(holder, position)
        cambiarImagencitaDeNico(holder, position)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                val fragmentAnotherImage = AnotherImage()
                val args = Bundle()
                args.putString("nombre",name[position])
                args.putInt("imagen", images[position])
                fragmentAnotherImage.setArguments(args)
                val numero = name[position]
                Toast.makeText(context,"el nombre del perro es "+ numero,Toast.LENGTH_LONG).show()
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragmentAnotherImage).addToBackStack(null).commit()
            }
        }

        )


    }

    override fun getItemCount(): Int {

        return name.size
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