package com.example.rickandmorty.API

import com.example.rickandmorty.Cache.CacheKeys
import com.example.rickandmorty.Cache.CacheManager
import com.example.rickandmorty.Models.CharacterListModel
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
        CoroutineScope(Dispatchers.IO).launch {

            var endpoint = "character"
            if (page != null) {
                if (!page?.isEmpty()!!) {
                    endpoint = endpoint + "?page=" + page
                }
            }
            val call = getRetrofit().create(ApiService::class.java).getCharacterList(endpoint)

            val response = call.body()
            if (call.isSuccessful) {

                CacheManager.deleteCache()
                var cacheKey: String
                if (page != null && !page.isEmpty()) {
                    cacheKey = CacheKeys.GetAllCharacters.toString()+"0"

                } else {
                    cacheKey = CacheKeys.GetAllCharacters.toString()+page
                }
                CacheManager.addToCache(key = cacheKey, value = response!!)


            } else {
                print("error: "+call.errorBody())
            }
        }
    }

}