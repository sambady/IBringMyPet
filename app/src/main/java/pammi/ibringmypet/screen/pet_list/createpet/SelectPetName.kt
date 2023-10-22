package pammi.ibringmypet.screen.pet_list.createpet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun selectPetName(navContoller: NavController, petModel: CreatePetViewModel) {
    var petName by remember { mutableStateOf(petModel.petName) }

    var nextButtonEnabled by remember { mutableStateOf(petModel.petName.isNotEmpty()) }

    CreatePetScaffold(
        navController = navContoller,
        progressStep = ProgressStep.PetName,
        backButtonAvailable = true,
        bottomBarContent = {
            Column(modifier = Modifier.absolutePadding(left = 16.dp, right = 16.dp)) {
                Button(
                    onClick = {
                        navContoller.navigate(ProgressStep.PetBreed.navigationName)
                    }, enabled = nextButtonEnabled, modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        "Next", fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Select pet name", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = petName,
                onValueChange = {
                    petName = it
                    petModel.petName = it
                    nextButtonEnabled = petName.isNotEmpty()
                },
                label = { },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (petName.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Back",
                            modifier = Modifier.clickable {
                                petName = ""
                                petModel.petName = ""
                                nextButtonEnabled = petName.isNotEmpty()
                            })
                    }
                }
            )
        }
    }
}


@Composable
@Preview
fun selectNamePreview() {
    selectPetName(navContoller = rememberNavController(), petModel = CreatePetViewModel())
}
