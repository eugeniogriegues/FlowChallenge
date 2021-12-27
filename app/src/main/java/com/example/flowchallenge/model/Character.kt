package com.example.flowchallenge.model

import com.squareup.moshi.Json


data class Character (

    @Json (name="id") val id: Int,

    @Json(name="name") val name: String,

    @Json (name="image")val image: String

)

data class CharacterResponse (
    @Json(name="results") val result : List<Character>
)