package com.example.rickandmorty.Cache

import android.app.ActivityManager
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.LruCache
import androidx.core.content.ContextCompat.getSystemService
import com.example.rickandmorty.Models.CharacterListModel
import okhttp3.internal.Internal.instance

object CacheManager {

    private var cache = HashMap<String, CharacterListModel>()


    fun addToCache(key: String, value: CharacterListModel){
        cache[key] = value
    }
    fun getFromCache(key: String): CharacterListModel? {
        return cache[key]
    }
    fun deleteCache(){
        cache = HashMap<String, CharacterListModel>()
    }

}