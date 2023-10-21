package pammi.ibringmypet.screen.createpet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
internal fun selectPetBreed(navContoller : NavController, petModel: CreatePetViewModel) {
    val possibleBreeds = listOf(
        "taksa normal", "taksa mini", "taksa rabbit", "pudel"
    )

    var nextButtonEnabled by remember { mutableStateOf(false) }

    var searchResult by remember { mutableStateOf(mutableListOf<String>()) }

    var breedName by remember { mutableStateOf("") }

    Column {
        TextField(
            value = breedName,
            onValueChange = {
                breedName = it
                petModel.petBreed = it
                nextButtonEnabled = breedName.isNotEmpty()

                searchResult = possibleBreeds.filter {
                    it.contains(breedName)
                }.toMutableList()

            },
            label = { Text("Breed") },
            singleLine = true
        )

        searchResult.forEach {
            Text(text = it, modifier = Modifier.clickable {
                breedName = it
                searchResult.clear()
            })
        }
    }

    Button(onClick = {
        navContoller.navigate(ProgressStep.PetBirthday.navigationName)
    }, enabled = nextButtonEnabled)
    {
        Text("Next")
    }
}