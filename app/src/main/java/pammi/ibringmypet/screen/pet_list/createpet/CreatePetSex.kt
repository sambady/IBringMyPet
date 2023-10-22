package pammi.ibringmypet.screen.pet_list.createpet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
internal fun selectPetSex(navContoller: NavController, petModel: CreatePetViewModel) {
    var selectedPetType by remember { mutableStateOf(petModel.petType) }

    var nextButtonEnabled by remember { mutableStateOf(petModel.petType.isNotEmpty()) }

    @Composable
    fun SelectPetSexButton(petSex: String) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = if (selectedPetType === petSex) Color.Red else Color.Gray),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(100.dp).height(100.dp),
            onClick = {
                selectedPetType = petSex
                nextButtonEnabled = true
                petModel.petSex = petSex
            })
        {
            Text(petSex)
        }
    }

    CreatePetScaffold(
        navController = navContoller,
        progressStep = ProgressStep.PetType,
        backButtonAvailable = false,
        bottomBarContent = {
            Column(modifier = Modifier.absolutePadding(left = 16.dp, right = 16.dp))
            {
                Button(
                    onClick = {
                        navContoller.navigate(ProgressStep.PetName.navigationName)
                    }, enabled = nextButtonEnabled, modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        "Next", fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    )
    {
        Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Select pet sex", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth().absolutePadding(left = 16.dp, right = 16.dp)
            ) {
                SelectPetSexButton("Boy")
                SelectPetSexButton("Girl")
            }
        }
    }
}

@Composable
@Preview
fun selectSexPreview() {
    selectPetSex(navContoller = rememberNavController(), petModel = CreatePetViewModel())
}
