package com.example.regions.models


import com.fasterxml.jackson.annotation.JsonProperty

data class Language(
    @JsonProperty("code")
    val code: String,
    @JsonProperty("iso639_2")
    val iso6392: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("nativeName")
    val nativeName: String
)