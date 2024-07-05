package com.example.apipractice.view.anime_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apipractice.data.model.Data
import com.example.apipractice.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    //StateFlow
    private val _animeList = MutableStateFlow <List<Data>> (emptyList())
    val animeList: MutableStateFlow <List<Data>> = _animeList

    init {
        getAllAnimes()
    }

    private fun getAllAnimes() {
       viewModelScope.launch {
           val animesList = apiRepository.getAllAnimes().data

           if(animesList!=null){
               Log.d("AnimeListViewModel", animesList.toString())
               _animeList.value = animesList
           }

       }
    }
}