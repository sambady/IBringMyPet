package pammi.ibringmypet.screen.pet_list.createpet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pammi.ibringmypet.MainBottomBar
import pammi.ibringmypet.Pet
import pammi.ibringmypet.PetManager
import pammi.ibringmypet.utils.DatePicker

@Composable
internal fun selectPetBirthday(navContoller: NavController, petModel: CreatePetViewModel) {
    var nextButtonEnabled by remember { mutableStateOf(petModel.petBirthday.isNotEmpty()) }

    CreatePetScaffold(
        navController = navContoller,
        progressStep = ProgressStep.PetBirthday,
        backButtonAvailable = true,
        bottomBarContent = {
            Column(modifier = Modifier.absolutePadding(left = 16.dp, right = 16.dp)) {
                Button(
                    onClick = {
                        PetManager.pets.value.add(
                            Pet(
                                petType = petModel.petType,
                                petBreed = petModel.petBreed,
                                petName = petModel.petName
                            )
                        )

                        navContoller.navigate(MainBottomBar.PetList.navigationName)
                    }, enabled = nextButtonEnabled, modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        "Complete", fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    )
    {

        DatePicker(onValueChange = {
            nextButtonEnabled = it.isNotEmpty()
            petModel.petBirthday = it
        }, defautlValue = petModel.petBirthday)
    }
}