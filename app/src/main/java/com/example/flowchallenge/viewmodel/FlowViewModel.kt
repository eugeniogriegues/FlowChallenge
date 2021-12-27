package com.example.flowchallenge.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowchallenge.model.ApiClient
import com.example.flowchallenge.model.CharacterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class FlowViewModel : ViewModel() {


    val flowModel = MutableLiveData<CharacterResponse>()

    val mensajesModel = MutableLiveData<String>()

    var page = 1;



    fun backPage () {    // BOTON PAGINA ANTERIOR



        if (page==1) {


            mensajesModel.postValue("ESTA ES LA PRIMER PAGINA")


        } else {
            page--

            obtenerItems(page)

        }


    }

    fun nextPage ()   {  // BOTON PAGINA SIGUIENTE

        if (page==42) {

            mensajesModel.postValue("ESTA ES LA ULTIMA PAGINA")



        } else {
            page++

            obtenerItems(page)

        }

    }



    fun obtenerItems (page: Int) {    //  CONSULTA AL API CON RETROFIT


            val client = ApiClient.apiService.getCharacters(page) // todo LANZA EL CALL POR NRO DE PAGINA


            // TODO ***************** INICIO CORRUTINA *************************************************

            CoroutineScope(Dispatchers.IO).launch {

                client.enqueue(object : retrofit2.Callback<CharacterResponse> {

                    override fun onResponse(

                        call: Call<CharacterResponse>,
                        response: Response<CharacterResponse>

                    ) {

                        if (response.isSuccessful) {

                            val result = response.body()?.result

                            result?.let {

                                flowModel.postValue(CharacterResponse(result))


                            }

                        }


                    }

                    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {

                        mensajesModel.postValue("SIN CONEXION A INTERNET") // todo SI FALLA EL CALL MUESTRA MSJ


                    }

                })
            }

        }


}




