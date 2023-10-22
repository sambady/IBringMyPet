package pammi.ibringmypet.screen.createpet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import pammi.ibringmypet.utils.sharedViewModel

fun registerCreatePetNavigation(navGraphBuilder: NavGraphBuilder, navController : NavController, routeName : String) {
    navGraphBuilder.navigation(startDestination = ProgressStep.PetType.navigationName, route = routeName) {

        ProgressStep.values().forEach { progressStep ->
            composable(progressStep.navigationName) {
                val viewModel = it.sharedViewModel<CreatePetViewModel>(navController = navController)
                when (progressStep) {
                    ProgressStep.PetType -> selectPetType(navController, viewModel)
                    ProgressStep.PetName -> selectPetName(navController, viewModel)
                    ProgressStep.PetBreed -> selectPetBreed(navController, viewModel)
                    ProgressStep.PetBirthday -> selectPetBirthday(navController, viewModel)
                }
            }
        }
    }
}

@Composable
@Preview
fun TestCreateType() {
    selectPetType(rememberNavController(), CreatePetViewModel())
}