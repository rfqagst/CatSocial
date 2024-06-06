package com.example.catsocial.presentation.screen.adoption

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.room.entity.Cat
import com.example.catsocial.data.room.repository.AdoptionRepository
import com.example.catsocial.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptionViewModel @Inject constructor(
    private val repository: AdoptionRepository
) : ViewModel() {

    private val _allAdoptions = MutableStateFlow<Resource<List<Cat>>>(Resource.Loading())
    val allAdoptions: StateFlow<Resource<List<Cat>>> = _allAdoptions

    private val _adoption = MutableStateFlow<Resource<Cat>>(Resource.Loading())
    val adoption: StateFlow<Resource<Cat>> = _adoption

    init {
        fetchAllAdoptions()
    }


    private fun fetchAllAdoptions() {
        viewModelScope.launch {
            repository.getAllAdoptions().collect { resource ->
                _allAdoptions.value = resource
            }
        }
    }

    fun insertAdoption(cat: Cat) {
        viewModelScope.launch {
            repository.insertAdoption(cat)
        }
    }

    fun deleteAdoption(cat: Cat) {
        viewModelScope.launch {
            repository.deleteAdoption(cat)
        }
    }

    fun getCatById(id: Int) {
        viewModelScope.launch {
            _adoption.value = Resource.Loading()
            val adoption = repository.getAdoptionById(id)
            _adoption.value = adoption
        }
    }


}