package com.example.learningroomandretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningroomandretrofit.ui.Route
import com.example.learningroomandretrofit.ui.add_person.AddPersonScreen
import com.example.learningroomandretrofit.ui.persons.PersonsScreen
import com.example.learningroomandretrofit.ui.theme.LearningRoomAndRetrofitTheme
import com.example.learningroomandretrofit.ui.update_person.UpdatePersonDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningRoomAndRetrofitTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.AddPersonScreen.name
                ) {
                    composable(route = Route.AddPersonScreen.name) {
                        AddPersonScreen(
                            onPersonsClick = {
                                navController.navigate(route = Route.PersonsScreen.name)
                            }
                        )
                    }
                    composable(route = Route.PersonsScreen.name) {
                        PersonsScreen(
                            onUpdate = {
                                navController.navigate(route = Route.UpdatePersonDialog.name + "/${it.id}")
                            }
                        )
                    }
                    dialog(
                        route = Route.UpdatePersonDialog.name + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val personId: Int = it.arguments?.getInt("id")!!
                        UpdatePersonDialog(
                            personId = personId,
                            onUpdate = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}