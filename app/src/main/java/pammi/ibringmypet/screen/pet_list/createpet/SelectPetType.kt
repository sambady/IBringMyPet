package pammi.ibringmypet.screen.pet_list.createpet

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
internal fun selectPetType(navContoller: NavController, petModel: CreatePetViewModel) {
    var selectedPetType by remember { mutableStateOf(petModel.petType) }

    var nextButtonEnabled by remember { mutableStateOf(petModel.petType.isNotEmpty()) }

    @Composable
    fun SelectPetTypeButton(petType: String) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = if (selectedPetType === petType) Color.Red else Color.Gray),
            onClick = {
                selectedPetType = petType
                nextButtonEnabled = true
                petModel.petType = petType
            }) {
            Text(petType)
        }
    }

    CreatePetScaffold(navContoller, ProgressStep.PetType, false) {
        SelectPetTypeButton("Dog")
        SelectPetTypeButton("Cat")
        SelectPetTypeButton("Other")

        Button(onClick = {
            navContoller.navigate(ProgressStep.PetName.navigationName)
        }, enabled = nextButtonEnabled)
        {
            Text("Next")
        }
    }
}


