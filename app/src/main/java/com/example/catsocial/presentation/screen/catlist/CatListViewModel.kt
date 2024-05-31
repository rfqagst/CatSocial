package com.example.catsocial.presentation.screen.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.retrofit.repository.CatRepository
import com.example.catsocial.data.retrofit.response.CatResponse
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

    private val _cats = MutableStateFlow<Resource<List<CatResponse>>>(Resource.Loading())
    val cats: StateFlow<Resource<List<CatResponse>>> = _cats

    init {
        fetchCat()
    }

    private fun fetchCat() {
        viewModelScope.launch {
            repository.fetchCat().collect {
                _cats.value = it
            }
        }
    }


}