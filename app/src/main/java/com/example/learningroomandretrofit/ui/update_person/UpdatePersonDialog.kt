package com.example.learningroomandretrofit.ui.update_person

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learningroomandretrofit.data.Person
import com.example.learningroomandretrofit.ui.theme.LearningRoomAndRetrofitTheme

@Composable
fun UpdatePersonDialog(
    updatePersonViewModel: UpdatePersonViewModel = hiltViewModel(),
    onUpdate: () -> Unit,
    personId: Int
) {
    LaunchedEffect(key1 = true) {
        updatePersonViewModel.addPerson(personId)
    }
    UpdatePersonDialog(
        person = updatePersonViewModel.person,
        onUpdate = {
            updatePersonViewModel.updatePerson(it)
            onUpdate()
        },
        onFirstNameChange = {
            updatePersonViewModel.onFirstNameChange(it)
        },
        onLastNameChange = {
            updatePersonViewModel.onLastNameChange(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePersonDialog(
    person: Person,
    onUpdate: (Person) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Update person dialog",
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                value = person.firstName.toString(),
                onValueChange = onFirstNameChange,
                label = {
                    Text(text = "First name")
                },
                placeholder = {
                    Text(text = "Enter person's first name")
                },
                singleLine = true
            )
            OutlinedTextField(
                value = person.lastName.toString(),
                onValueChange = onLastNameChange,
                label = {
                    Text(text = "Last name")
                },
                placeholder = {
                    Text(text = "Enter person's last name")
                },
                singleLine = true
            )
            Button(
                onClick = {
                    onUpdate(person)
                }
            ) {
                Text(text = "Update person")
            }
        }
    }
}

@Preview(
    name = "UpdatePersonDialog",
    showBackground = true
)
@Preview(
    name = "UpdatePersonDialog (Dark)",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun UpdatePersonDialogPreview() {
    LearningRoomAndRetrofitTheme(
        dynamicColor = false
    ) {
        UpdatePersonDialog(
            person = Person(1, "Marko", "Markovic"),
            onUpdate = {},
            onFirstNameChange = {},
            onLastNameChange = {}
        )
    }
}