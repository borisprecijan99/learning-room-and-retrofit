package com.example.learningroomandretrofit.ui.add_person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningroomandretrofit.data.Person
import com.example.learningroomandretrofit.data.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {
    var uiState by mutableStateOf(AddPersonUiState("", ""))
        private set

    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.addPerson(person)
            uiState = uiState.copy(
                firstName = "",
                lastName = ""
            )
        }
    }

    fun onFirstNameChange(firstName: String) {
        uiState = uiState.copy(
            firstName = firstName
        )
    }

    fun onLastNameChange(lastName: String) {
        uiState = uiState.copy(
            lastName = lastName
        )
    }
}