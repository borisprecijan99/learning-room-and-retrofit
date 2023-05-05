package com.example.learningroomandretrofit.ui.update_person

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
class UpdatePersonViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {
    var person: Person by mutableStateOf(Person())
        private set

    fun addPerson(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            person = personRepository.getPersonById(personId)
        }
    }

    fun updatePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.updatePerson(person)
        }
    }

    fun onFirstNameChange(firstName: String) {
        person = person.copy(
            firstName = firstName
        )
    }

    fun onLastNameChange(lastName: String) {
        person = person.copy(
            lastName = lastName
        )
    }
}