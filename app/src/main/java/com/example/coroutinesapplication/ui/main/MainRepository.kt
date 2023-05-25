package com.example.coroutinesapplication.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {

    //callbacks
    fun getGames(callback: (games: List<DataGames>) -> Unit){
        Thread(Runnable { Thread.sleep(5000)
            callback.invoke( listOf(
                DataGames(1,"GTA"),
                DataGames(2,"FIFA"),
                DataGames(3,"COD"),
            ))

        }).start()

    }
    //coroutines
    suspend fun getGamesCoroutines(): List<DataGames>{
       return withContext(Dispatchers.Default){
            delay(5000)
           listOf(
               DataGames(1,"GTA"),
               DataGames(2,"FIFA"),
               DataGames(3,"COD"),
           )
        }
    }
}