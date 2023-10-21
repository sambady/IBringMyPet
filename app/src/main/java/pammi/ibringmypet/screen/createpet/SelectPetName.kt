package pammi.ibringmypet.screen.createpet

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
internal fun createPetName(navContoller: NavController, petModel: CreatePetViewModel) {
    var petName by remember { mutableStateOf("") }

    var nextButtonEnabled by remember { mutableStateOf(false) }
    TextField(
        value = petName,
        onValueChange = {
            petName = it
            petModel.petName = it
            nextButtonEnabled = petName.isNotEmpty()
        },
        label = { Text("Pet name") },
        singleLine = true
    )

    Button(onClick = {
        navContoller.navigate(ProgressStep.PetBreed.navigationName)
    }, enabled = nextButtonEnabled)
    {
        Text("Next")
    }
}