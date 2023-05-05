package com.example.learningroomandretrofit.ui

sealed class Route(val name: String) {
    object AddPersonScreen: Route("add_person")
    object PersonsScreen: Route("persons")
    object UpdatePersonDialog: Route("update_person")
}
