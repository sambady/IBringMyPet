package pammi.ibringmypet.screen.pet_list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pammi.ibringmypet.MainBottomBar
import pammi.ibringmypet.MainScaffold
import pammi.ibringmypet.NestedNavigation
import pammi.ibringmypet.PetManager

@Composable
fun ScreenPetList(navController : NavController) {
    MainScaffold(navController, MainBottomBar.PetList) {

        Text(fontSize = 32.sp, text = "Pet list")

        PetManager.pets.value.forEach {
            Text("Pet ${it.petName}")
        }

        Button(onClick = {
            navController.navigate(NestedNavigation.CreatePet.navigationName)
        })
        {
            Text("Create Pet")
        }
    }
}