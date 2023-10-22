package pammi.ibringmypet.screen.createpet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import pammi.ibringmypet.MainBottomBar


enum class ProgressStep(val navigationName : String, val stepNumber: UInt, val description: String, val progress: Float) {
    PetType("pet_type",1u, "Select pet type", 0.25f),
    PetName("pet_name",2u, "Enter pet name", 0.5f),
    PetBreed("pet_breed", 3u, "Enter pet breed", 0.75f),
    PetBirthday("pet_birthday", 4u, "Enter birthday", 1.0f)
}

class CreatePetViewModel(
    var petType : String = "",
    var petName : String= "",
    var petBreed : String= "",
    var petBirthday : String = ""
)  : ViewModel()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePetScaffold(navController: NavController, progressStep: ProgressStep, backButtonAvailable : Boolean, content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                actions = {
                    if (backButtonAvailable) {
                        Button(onClick = {
                            navController.navigateUp()
                        }) {
                            Text("Back")
                        }
                    }
                    Button(onClick = { navController.navigate(MainBottomBar.PetList.navigationName) }) {
                        Text("Skip")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                //modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${progressStep.stepNumber} of ${
                    ProgressStep.values().last().stepNumber
                }".trimIndent(),
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                progress = progressStep.progress
            )

            content()
        }
    }
}

