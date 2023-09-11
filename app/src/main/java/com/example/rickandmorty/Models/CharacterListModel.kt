package com.example.rickandmorty.Models

data class CharacterListModel(
    var info: InfoModel? = null,
    var results: Array<CharacterModel>? = null
)
