package pammi.ibringmypet.screen.createpet

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import pammi.ibringmypet.MainBottomBar
import pammi.ibringmypet.Pet
import pammi.ibringmypet.PetManager
import pammi.ibringmypet.utils.DatePicker

@Composable
internal fun selectPetBirthday(navContoller: NavController, petModel: CreatePetViewModel) {
    var nextButtonEnabled by remember { mutableStateOf(petModel.petBirthday.isNotEmpty()) }

    CreatePetScaffold(navContoller, ProgressStep.PetBirthday, true) {

        DatePicker(onValueChange = {
            nextButtonEnabled = it.isNotEmpty()
            petModel.petBirthday = it
        }, defautlValue = petModel.petBirthday)

        Button(onClick = {
            PetManager.pets.value.add(
                Pet(
                    petType = petModel.petType,
                    petBreed = petModel.petBreed,
                    petName = petModel.petName
                )
            )

            navContoller.navigate(MainBottomBar.PetList.navigationName)
        }, enabled = nextButtonEnabled)
        {
            Text("Complete")
        }
    }
}