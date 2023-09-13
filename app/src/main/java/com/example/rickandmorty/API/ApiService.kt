package com.example.rickandmorty.API

import com.example.rickandmorty.Models.CharacterListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getCharacterList(@Url url: String): Response<CharacterListModel>

}