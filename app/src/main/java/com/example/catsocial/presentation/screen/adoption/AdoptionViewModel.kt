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

    private val _searchAdoption = MutableStateFlow<Resource<List<Cat>>>(Resource.Loading())
    val searchAdoption: StateFlow<Resource<List<Cat>>> = _searchAdoption

    private val _adoptionDetail = MutableStateFlow<Resource<Cat>>(Resource.Loading())
    val adoptionDetail: StateFlow<Resource<Cat>> = _adoptionDetail

    private val _insertAdoptionState = MutableStateFlow<Resource<Unit>>(Resource.Idle())
    val insertAdoptionState: StateFlow<Resource<Unit>> = _insertAdoptionState

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
            _insertAdoptionState.value = Resource.Loading()
            try {
                repository.insertAdoption(cat)
                _insertAdoptionState.value = Resource.Success(Unit)
            } catch (e: Exception) {
                _insertAdoptionState.value =
                    Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

    fun deleteAdoption(cat: Cat) {
        viewModelScope.launch {
            repository.deleteAdoption(cat)
        }
    }

    fun searchCatByRace(race: String) {
        viewModelScope.launch {
            repository.searchCatsByRace(race).collect { resource ->
                _searchAdoption.value = resource
            }
        }
    }


    fun getCatById(id: Int) {
        viewModelScope.launch {
            repository.getAdoptionById(id).collect { resource ->
                _adoptionDetail.value = resource
            }

        }
    }


}