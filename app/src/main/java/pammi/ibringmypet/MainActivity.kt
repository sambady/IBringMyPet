package pammi.ibringmypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pammi.ibringmypet.screen.pet_list.createpet.registerCreatePetNavigation
import pammi.ibringmypet.screen.pet_list.ScreenPetList


enum class MainBottomBar(val buttonName : String, val navigationName : String)
{
    Main("main", "main"),
    PetList("pet list", "pet_list"),
    Calendar("calendar", "calendar")
}

enum class NestedNavigation(val navigationName : String)
{
    CreatePet("create_pet")
}

@Composable
@Preview
fun MainMenu()
{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainBottomBar.Main.navigationName) {

        MainBottomBar.values().forEach {currentButton ->
            composable(currentButton.navigationName) {
                when(currentButton) {
                    MainBottomBar.PetList -> ScreenPetList(navController = navController)
                    else -> {
                        MainScaffold(navController, currentButton) {
                            Text(text = currentButton.navigationName, fontSize = 32.sp)
                        }
                    }
                }
            }
        }

        registerCreatePetNavigation(this, navController, NestedNavigation.CreatePet.navigationName)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainMenu()
        }
    }
}


