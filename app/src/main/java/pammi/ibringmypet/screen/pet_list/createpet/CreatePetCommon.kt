package pammi.ibringmypet.screen.pet_list.createpet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import pammi.ibringmypet.MainBottomBar


enum class ProgressStep(
    val navigationName: String,
    val stepNumber: UInt,
    val description: String,
    val progress: Float
) {
    PetType("pet_type", 1u, "Select pet type", 0.2f),
    PetSex("pet_sex", 2u, "Select pet sex", 0.4f),
    PetName("pet_name", 3u, "Enter pet name", 0.6f),
    PetBreed("pet_breed", 4u, "Enter pet breed", 0.8f),
    PetBirthday("pet_birthday", 5u, "Enter birthday", 1.0f)
}

class CreatePetViewModel(
    var petType: String = "",
    var petSex : String = "",
    var petName: String = "",
    var petBreed: String = "",
    var petBirthday: String = ""
) : ViewModel()

@Composable
fun ProgressBar(progressStep: ProgressStep) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(30.dp)
    )
    {
        Text(
            //modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${progressStep.stepNumber} of ${
                ProgressStep.values().last().stepNumber
            }", fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(),
            progress = progressStep.progress
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePetScaffold(
    navController: NavController,
    progressStep: ProgressStep,
    backButtonAvailable: Boolean,
    bottomBarContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.White
                ),
                title = { ProgressBar(progressStep) },
                navigationIcon = {
                    if(progressStep != ProgressStep.values().first()) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(MainBottomBar.PetList.navigationName)
                        },
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    bottomBarContent()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            content()
        }

    }
}

