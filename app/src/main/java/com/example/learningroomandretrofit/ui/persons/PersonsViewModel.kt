package com.example.learningroomandretrofit.ui.persons

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningroomandretrofit.data.Person
import com.example.learningroomandretrofit.data.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {
    var uiState by mutableStateOf(PersonsUiState(data = emptyList()))
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                uiState = uiState.copy(
                    isLoading = true
                )
            }
            personRepository.getAll().collectLatest {
                withContext(Dispatchers.Main) {
                    uiState = uiState.copy(
                        data = it,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun deletePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePerson(person)
        }
    }
}