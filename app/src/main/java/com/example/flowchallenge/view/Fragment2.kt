package com.example.flowchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.example.flowchallenge.R

class Fragment2 : Fragment(R.layout.fragment_detalle) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (arguments != null) {

            val getid = requireArguments().getString("id")
            val getnombre = requireArguments().getString("nombre")
            val getimagen = requireArguments().getString("imagen")

            val id = requireView().findViewById<TextView>(R.id.fragment_detail_id)
            val name = requireView().findViewById<TextView>(R.id.fragment_detail_nombre)
            val image = requireView().findViewById<ImageView>(R.id.fragment_detail_imagen)


            id.text = getid
            name.text = getnombre
            image.load(getimagen)

        }

    }

}