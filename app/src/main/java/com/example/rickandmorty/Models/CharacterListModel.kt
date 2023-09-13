package com.example.rickandmorty.Models

import com.google.gson.annotations.SerializedName

data class CharacterListModel(
    @SerializedName("info") var info: InfoModel? = null,
    @SerializedName("results") var results: Array<CharacterModel>? = null
)
