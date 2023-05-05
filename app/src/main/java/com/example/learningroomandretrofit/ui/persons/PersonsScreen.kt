package com.example.learningroomandretrofit.ui.persons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learningroomandretrofit.data.Person
import com.example.learningroomandretrofit.ui.theme.LearningRoomAndRetrofitTheme

@Composable
fun PersonsScreen(
    personsViewModel: PersonsViewModel = hiltViewModel(),
    onUpdate: (Person) -> Unit
) {
    PersonsScreen(
        personsUiState = personsViewModel.uiState,
        onUpdate = onUpdate,
        onDelete = {
            personsViewModel.deletePerson(it)
        }
    )
}

@Composable
private fun PersonsScreen(
    personsUiState: PersonsUiState,
    onUpdate: (Person) -> Unit,
    onDelete: (Person) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (personsUiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.dp)
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(10.dp)
            ) {
                items(items = personsUiState.data) {
                    Person(
                        person = it,
                        onUpdate = onUpdate,
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

@Composable
fun Person(
    person: Person,
    onDelete: (Person) -> Unit,
    onUpdate: (Person) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "${person.firstName} ${person.lastName}",
                style = MaterialTheme.typography.titleLarge
            )
            Row {
                Icon(
                    imageVector = Icons.Outlined.Update,
                    contentDescription = "Update Icon",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable {
                            onUpdate(person)
                        }
                )
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete Icon",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable {
                            onDelete(person)
                        }
                )
            }
        }
    }
}

@Preview(name = "PersonsScreen", showSystemUi = true)
@Preview(
    name = "PersonsScreen (Dark)",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PersonsScreenPreview() {
    LearningRoomAndRetrofitTheme(
        dynamicColor = false
    ) {
        PersonsScreen(
            personsUiState = PersonsUiState(
                data = listOf(
                    Person(1, "Boris", "Precijan"),
                    Person(2, "Petar", "Petrovic")
                )
            ),
            onUpdate = {},
            onDelete = {}
        )
    }
}