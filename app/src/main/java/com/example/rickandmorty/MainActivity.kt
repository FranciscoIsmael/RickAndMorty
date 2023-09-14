package com.example.rickandmorty

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.rickandmorty.API.Provider
import com.example.rickandmorty.Cache.CacheKeys
import com.example.rickandmorty.Cache.CacheManager
import com.example.rickandmorty.Models.CharacterListModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    var provider = Provider()
    lateinit var charcters: CharacterListModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Button(onClick = {
                        provider.getAllCharacters(page = null)
                        charcters = CacheManager.getFromCache(CacheKeys.GetAllCharacters.toString()+"null")!!
                        print(charcters)
                    }) {
                        Text("click")
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RickAndMortyTheme {
//        Greeting("Android")
//    }
//}