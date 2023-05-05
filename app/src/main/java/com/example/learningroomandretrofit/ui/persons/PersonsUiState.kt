package com.example.learningroomandretrofit.ui.persons

import com.example.learningroomandretrofit.data.Person

data class PersonsUiState(
    val data: List<Person>,
    val isLoading: Boolean = false
)