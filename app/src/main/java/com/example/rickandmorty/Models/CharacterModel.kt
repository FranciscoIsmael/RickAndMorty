package com.example.rickandmorty.Models

import com.google.gson.annotations.SerializedName

data class CharacterModel(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("origin") var origin: Origin?,
    @SerializedName("location") var location: Location?,
    @SerializedName("image") var image: String? = null,
    @SerializedName("episode") var episode: Array<String>? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null

)