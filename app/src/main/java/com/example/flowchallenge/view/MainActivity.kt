package com.example.flowchallenge.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.flowchallenge.R
import com.example.flowchallenge.databinding.ActivityMainBinding
import com.example.flowchallenge.viewmodel.FlowViewModel

class MainActivity : AppCompatActivity() {

    val f = supportFragmentManager

    lateinit var binding: ActivityMainBinding

    private val flowViewModel : FlowViewModel by viewModels ()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // TODO ************************************************************************************

        if (savedInstanceState==null) { flowViewModel.obtenerItems(1) } // CARGA INICIAL

        // TODO ************************************************************************************



        flowViewModel.mensajesModel.observe(this, androidx.lifecycle.Observer {

            mostrarMensaje(it)



        })


        flowViewModel.flowModel.observe(this, androidx.lifecycle.Observer {


            iniciarRecycler(it.result)  // INICIA EL RECYCLERVIEW CON LOS RESULTADOS DEL GET ITEMS

        })

        // TODO ************************************************************************************

        replaceFragment (Fragment1()) // CARGA EL FRAGMENT DEL RECYCLERVIEW

        // TODO ************************************************************************************


        if (resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT) {

            iniciarFragmentoDetalle(Fragment2())    // CARGA EL FRAGMENTO DETALLE MASTER DETAIL

        }

        // TODO ************************************************************************************

        binding.btnAnterior.setOnClickListener {

                flowViewModel.backPage()  // BOTON PAGINA ANTERIOR
            }


        binding.btnSiguiente.setOnClickListener {

                flowViewModel.nextPage()  // BOTON PAGINA SIGUIENTE

            }

        // TODO ************************************************************************************

    }

    // TODO ***************************** METODO PARA INICIAR EL RECYCLERVIEW **********************

    private fun iniciarRecycler(result: List<com.example.flowchallenge.model.Character>) {


        val adapter = MainAdapter(result, f)  //TODO Le paso un FragmentManager como parametro

        val recyclerView = findViewById<RecyclerView>(R.id.rvPersonajes)

        recyclerView?.layoutManager =
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

        recyclerView?.adapter = adapter

    }

    // TODO ********************* METODO PARA REEMPLAZAR FRAGMENTOS ********************************

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

    }
    // TODO ****************************************************************************************

    private fun iniciarFragmentoDetalle(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentDetail, fragment)     // todo INICIAR FRAGMENTO DETALLE

        transaction.commit()

    }
    // TODO ****************************************************************************************

    private fun mostrarMensaje (x: String) {     // todo METODO PARA MOSTRAR MENSAJES TOAST
        Toast.makeText(this,x, Toast.LENGTH_LONG ).show()
        }
    // TODO ****************************************************************************************


}

// Creada por Eugenio Griegues - 24/12/2021 - eugeniofiori@gmail.com


