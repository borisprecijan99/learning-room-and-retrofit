package com.example.learningroomandretrofit.ui.add_person

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learningroomandretrofit.data.Person
import com.example.learningroomandretrofit.ui.theme.LearningRoomAndRetrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun AddPersonScreen(addPersonViewModel: AddPersonViewModel = hiltViewModel(), onPersonsClick: () -> Unit) {
    val uiState = addPersonViewModel.uiState
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    AddPersonScreen(
        addPersonUiState = uiState,
        onFirstNameChange = {
            addPersonViewModel.onFirstNameChange(it)
        },
        onLastNameChange = {
            addPersonViewModel.onLastNameChange(it)
        },
        onAddPerson = {
            addPersonViewModel.addPerson(it)
        },
        onPersonsClick = onPersonsClick,
        onGetRandomQuote = {
            coroutineScope.launch {
                val job = async(Dispatchers.IO) {
                    addPersonViewModel.getRandomQuote()
                }
                val quote = job.await()
                Toast.makeText(context, "${quote.author}: ${quote.quote}", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddPersonScreen(
    addPersonUiState: AddPersonUiState,
    onFirstNameChange: (String) -> Unit = {},
    onLastNameChange: (String) -> Unit = {},
    onAddPerson: (Person) -> Unit = {},
    onPersonsClick: () -> Unit = {},
    onGetRandomQuote: () -> Unit = {}
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add new person to database",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = addPersonUiState.firstName,
                onValueChange = onFirstNameChange,
                modifier = Modifier
                    .padding(vertical = 5.dp),
                label = {
                    Text(text = "First name")
                },
                singleLine = true,
                placeholder = {
                    Text(text = "Enter person's first name")
                }
            )
            OutlinedTextField(
                value = addPersonUiState.lastName,
                onValueChange = onLastNameChange,
                modifier = Modifier.padding(vertical = 5.dp),
                label = {
                    Text(text = "Last name")
                },
                singleLine = true,
                placeholder = {
                    Text(text = "Enter person's last name")
                }
            )
            Button(
                onClick = {
                    val person = Person(
                        firstName = addPersonUiState.firstName,
                        lastName = addPersonUiState.lastName
                    )
                    onAddPerson(person)
                }
            ) {
                Text(text = "Add person")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Button(
                    onClick = onPersonsClick
                ) {
                    Text(text = "View added persons")
                }
                Button(
                    onClick = onGetRandomQuote
                ) {
                    Text(text = "Get random quote")
                }
            }
        }
    }
}

@Preview(name = "AddPersonScreen", showSystemUi = true)
@Preview(name = "AddPersonScreen (Dark)", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddPersonScreenPreview(){
    LearningRoomAndRetrofitTheme(
        dynamicColor = false
    ) {
        AddPersonScreen(addPersonUiState = AddPersonUiState(firstName = "Boris", lastName = "Precijan"))
    }
}