package pammi.ibringmypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pammi.ibringmypet.screen.createpet.registerCreatePetNavigation


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
fun mainScaffold(navController : NavController, currentSelection : MainBottomBar, content: @Composable () -> Unit)
{
    var selected by remember { mutableStateOf(MainBottomBar.Main)}
    Scaffold(
        bottomBar = {
            NavigationBar() {
                MainBottomBar.values().forEach {
                    NavigationBarItem(
                        selected = it == currentSelection,
                        onClick = {
                            navController.navigate(it.navigationName)
                        },
                        icon = {
                            Text(it.buttonName)
                        })
                }   
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}



@Composable
@Preview
fun MainMenu()
{
    val navController = rememberNavController()

    Column {
        NavHost(navController = navController, startDestination = MainBottomBar.Main.navigationName) {

            MainBottomBar.values().forEach {currentButton ->
                composable(currentButton.navigationName) {
                    mainScaffold(navController, currentButton) {
                        Text(currentButton.navigationName)

                        if(currentButton == MainBottomBar.PetList) {
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
                }
            }

            registerCreatePetNavigation(this, navController, NestedNavigation.CreatePet.navigationName)
        }
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


