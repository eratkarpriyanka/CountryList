package com.example.regions.models


import com.fasterxml.jackson.annotation.JsonProperty

data class RegionListDataItem(
    @JsonProperty("capital")
    val capital: String,
    @JsonProperty("code")
    val code: String,
    @JsonProperty("currency")
    val currency: Currency,
    @JsonProperty("demonym")
    val demonym: String,
    @JsonProperty("flag")
    val flag: String,
    @JsonProperty("language")
    val language: Language,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("region")
    val region: String
)