package com.example.flowchallenge.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    // CREAMOS UNA VARIABLE PARA EL MOSHI BUILDER, AGREGANDOLE UN CONVERSOR

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}


interface ApiService {

    @GET ("character")
    fun getCharacters (@Query("page") page: Int) : Call<CharacterResponse>

}