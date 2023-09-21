package com.example.rickandmorty.API

import com.example.rickandmorty.Cache.CacheKeys
import com.example.rickandmorty.Cache.CacheManager
import com.example.rickandmorty.Models.CharacterListModel
import com.example.rickandmorty.Models.FilterModel
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

    fun getAllCharacters(page: String?, result: (characters: CharacterListModel?, error: String) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            var currentPage = page
            if (currentPage == null || currentPage == ""){
                currentPage = "0"
            }

            val cacheData = CacheManager.getFromCache(CacheKeys.GetAllCharacters.toString()+currentPage)
            if (cacheData == null){

                var endpoint = "character"
                if (currentPage != null && !currentPage.isEmpty()) {
                    endpoint = endpoint + "/?page=" + currentPage
                }
                val call = getRetrofit().create(ApiService::class.java).getCharacterList(endpoint)
                val response = call.body()
                if (call.isSuccessful) {

                    CacheManager.deleteCache()
                    var cacheKey: String
                    cacheKey = CacheKeys.GetAllCharacters.toString()+currentPage
                    CacheManager.addToCache(key = cacheKey, value = response!!)

                    result(response, "Success")
                } else {
                    result(null, "Error calling the api character endpoint: "+call.errorBody())
                }

            } else {
                result(cacheData, "Success")
            }
        }

    }


    fun filterCharacters(filter: FilterModel, page: String?, result: (characters: CharacterListModel?, error: String) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {

            val cacheData: CharacterListModel?

            if (page != null && !page.isEmpty()) {
                cacheData = CacheManager.getFromCache(CacheKeys.GetAllCharacters.toString()+page)
            } else {
                cacheData = CacheManager.getFromCache(CacheKeys.GetAllCharacters.toString())
            }

            if (cacheData == null){

                var endpoint = "character/?"
                if (page != null && !page.isEmpty()) {
                    endpoint = endpoint + "page=" + page+"&"
                }

                if (filter.name != null) {
                    if (!filter.name!!.isEmpty()){
                        endpoint = endpoint+"name="+filter.name+"&"
                    }
                }
                if (filter.status != null) {
                    if (!filter.status!!.isEmpty()){
                        endpoint = endpoint+"status="+filter.status+"&"
                    }
                }
                if (filter.species != null) {
                    if (!filter.species!!.isEmpty()){
                        endpoint = endpoint+"species="+filter.species+"&"
                    }
                }
                if (filter.type != null) {
                    if (!filter.type!!.isEmpty()){
                        endpoint = endpoint+"type="+filter.type+"&"
                    }
                }
                if (filter.gender != null) {
                    if (!filter.gender!!.isEmpty()){
                        endpoint = endpoint+"gender="+filter.gender
                    }
                }

                if (endpoint.last() == '&'){
                    endpoint = endpoint.substring(0, endpoint.length - 1)
                }

                val call = getRetrofit().create(ApiService::class.java).getCharacterList(endpoint)
                val response = call.body()
                if (call.isSuccessful) {

                    CacheManager.deleteCache()
                    var cacheKey: String

                    if (page != null && !page.isEmpty()){
                        cacheKey = CacheKeys.GetAllCharacters.toString()+page
                    } else {
                        cacheKey = CacheKeys.GetAllCharacters.toString()
                    }
                    CacheManager.addToCache(key = cacheKey, value = response!!)

                    result(response, "Success")
                } else {
                    result(null, "Error calling the api character endpoint: "+call.errorBody())
                }

            } else {
                result(cacheData, "Success")
            }
        }

    }

}