package com.example.rickandmorty.API

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Provider {
    val baseUrl = "https://rickandmortyapi.com/api/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAllCharacters(page: String?){
        print("a")
        CoroutineScope(Dispatchers.IO).launch {
            var endpoint = "character"
            if (page != null) {
                if (!page?.isEmpty()!!) {
                    endpoint = endpoint + "?page=" + "page"
                }
            }
            val call = getRetrofit().create(ApiService::class.java).getCharacterList(endpoint)

            val response = call.body()
            if (call.isSuccessful) {
                print(response)
            } else {
                print("error: "+call.errorBody())
            }
        }
    }

}