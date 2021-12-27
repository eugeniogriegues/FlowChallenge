package com.example.flowchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.flowchallenge.databinding.ActivityDetalleNuevoBinding

class DetalleNuevo : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleNuevoBinding


    override fun onCreate(savedInstanceState: Bundle?) {     // TODO *** ACTIVITY DETALLE ***
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        binding.outputDetailId.text = extras?.getString("id")
        binding.outputDetailNombre.text = extras?.getString("nombre")
        binding.outputDetailImagen.load(extras?.getString("imagen"))

    }
}