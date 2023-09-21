package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmorty.API.Provider
import com.example.rickandmorty.Models.CharacterListModel
import com.example.rickandmorty.Models.CharacterModel
import com.example.rickandmorty.Models.FilterModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    var provider = Provider()
    lateinit var charcters: CharacterListModel
    lateinit var characterError: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Button(onClick = {
                        provider.getAllCharacters(page = "0", {characters, error ->
                            
                            if (error == "Success") {
                                charcters = characters!!
                            } else {
                                characterError = error!!
                            }

                        })

                    }) {
                        Text("get all characters")
                    }

                    Button(onClick = {
                        provider.filterCharacters(filter = FilterModel(name = "Rick", status = "alive", species = "human"), page = null, { characters, error ->

                            if (error == "Success") {
                                charcters = characters!!
                            } else {
                                characterError = error!!
                            }

                        })
                        //charcters = CacheManager.getFromCache(CacheKeys.GetAllCharacters.toString()+"null")!!
                        //print(charcters)
                    }) {
                        Text("filter characters")
                    }

                    //createList(characters = charcters.results!!)

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

@Composable
fun createList(characters: Array<CharacterModel>){
    LazyColumn(
        content = {
            characters.forEach {
                item{
                    it.name?.let { it1 -> Text(it1) }
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RickAndMortyTheme {
//        Greeting("Android")
//    }
//}