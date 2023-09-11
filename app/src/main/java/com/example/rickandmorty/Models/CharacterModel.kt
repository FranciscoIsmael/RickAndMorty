package com.example.rickandmorty.Models

data class CharacterModel(

    var id: Int? = null,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,
    var origin: Origin?,
    var location: Location?,
    var image: String? = null,
    var episode: Array<String>? = null,
    var url: String? = null,
    var created: String? = null

)