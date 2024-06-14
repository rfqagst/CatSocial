package com.example.catsocial.presentation.screen.catlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.retrofit.model.CatWithImage
import com.example.catsocial.data.retrofit.repository.CatRepository
import com.example.catsocial.data.retrofit.response.ResponseImage
import com.example.catsocial.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _cats = MutableStateFlow<Resource<List<CatWithImage>>>(Resource.Loading())
    val cats: StateFlow<Resource<List<CatWithImage>>> = _cats

    private val _searchCats = MutableStateFlow<Resource<List<CatWithImage>>>(Resource.Loading())
    val searchCats: StateFlow<Resource<List<CatWithImage>>> = _searchCats

    private val _catDetail = MutableStateFlow<Resource<CatWithImage>>(Resource.Loading())
    val catDetail: StateFlow<Resource<CatWithImage>> = _catDetail


    private val _imageCats = MutableStateFlow<Resource<ResponseImage>>(Resource.Loading())
    val imageCats: StateFlow<Resource<ResponseImage>> = _imageCats

    init {
        fetchCat()
    }

     fun fetchCat() {
        viewModelScope.launch {
            repository.fetchCat().collect {
                _cats.value = it
                Log.d("CatListViewModel", "Fetched Cats: $it")
            }
        }
    }

    fun searchCat(race: String) {
        viewModelScope.launch {
            repository.searchCatByRace(race).collect {
                _searchCats.value = it
            }
        }
    }

    fun fetchCatById(catId: String) {
        viewModelScope.launch {
            repository.fetchCatById(catId).collect {
                _catDetail.value = it
                Log.d("CatDetail", "fetchCatById: $it")
            }
        }
    }



}