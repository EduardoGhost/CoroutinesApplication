package com.example.coroutinesapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val gamesLifeData = MutableLiveData<List<DataGames>>()

    fun getGames(){
        repository.getGames { games ->
            gamesLifeData.postValue(games)
        }

    }

    fun getGamesCoroutines(){
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.Default) {
                repository.getGamesCoroutines()
            }
            gamesLifeData.value = games
        }
    }

    class MainViewModelFactory(
        private val repository: MainRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T{
            return MainViewModel(repository) as T

        }
    }

}
