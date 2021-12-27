package com.example.flowchallenge.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.flowchallenge.R
import com.example.flowchallenge.databinding.ItemBinding
import com.example.flowchallenge.model.Character

class MainAdapter (val charactersList: List<com.example.flowchallenge.model.Character>, val f : FragmentManager) : RecyclerView.Adapter<MainAdapter.MainViewHolder>()  {

    inner class MainViewHolder (private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemBinding.bind(itemView)

        init {               // TODO ****  listener para cada item del recyclerView ****

            itemView.setOnClickListener { v: View ->

                val position : Int = adapterPosition

                if(v.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

                    val intent = Intent (v.context.applicationContext,DetalleNuevo::class.java)

                    intent.putExtra("id", charactersList[position].id.toString())
                    intent.putExtra("nombre", charactersList[position].name)
                    intent.putExtra("imagen", charactersList[position].image)

                    v.context.startActivity(intent)

                    // todo ************************************************************************
                    /* val bundle =  Bundle ()

                    bundle.putString("id", charactersList[position].id.toString())
                    bundle.putString("nombre", charactersList[position].name)
                    bundle.putString("imagen", charactersList[position].image)

                    val transaction = f.beginTransaction()

                    val nuevoFragmento = Fragment2()    // todo/ OTRA OPCION:
                                                        // todo/ EN VEZ DE LANZAR OTRA ACTIVITY
                    nuevoFragmento.arguments = bundle   // todo/ REEMPLAZO EL FRAGMENT EN MAINaCTIV

                    transaction.replace(R.id.fragmentContainer, nuevoFragmento)

                    transaction.addToBackStack(null)

                    transaction.commit() */
                    // todo ************************************************************************

                } else {

                    val bundle =  Bundle ()

                    bundle.putString("id", charactersList[position].id.toString())
                    bundle.putString("nombre", charactersList[position].name)
                    bundle.putString("imagen", charactersList[position].image)

                    val transaction = f.beginTransaction()

                    val nuevoFragmento = Fragment2()

                    nuevoFragmento.arguments = bundle

                    transaction.replace(R.id.fragmentDetail, nuevoFragmento)

                    transaction.addToBackStack(null)

                    transaction.commit()

                }

            }

        }

        fun bindData (character: Character) {

            binding.outputNombre.text = character.name
            binding.outputImagen.load(character.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {

        return charactersList.size
    }

}