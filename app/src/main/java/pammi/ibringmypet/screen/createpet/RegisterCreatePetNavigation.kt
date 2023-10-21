package pammi.ibringmypet.screen.createpet

import androidx.compose.foundation.layout.Column
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pammi.ibringmypet.utils.sharedViewModel

fun registerCreatePetNavigation(navGraphBuilder: NavGraphBuilder, navController : NavController, routeName : String) {
    navGraphBuilder.navigation(startDestination = ProgressStep.PetType.navigationName, route = routeName) {

        ProgressStep.values().forEach { progressStep ->
            composable(progressStep.navigationName) {
                val viewModel = it.sharedViewModel<CreatePetViewModel>(navController = navController)
                Column {
                    when (progressStep) {
                        ProgressStep.PetType -> createPetScafflold(
                            navController,
                            progressStep,
                            false
                        ) {
                            selectPetType(navController, viewModel)
                        }

                        ProgressStep.PetName -> createPetScafflold(
                            navController,
                            progressStep,
                            true
                        ) {
                            createPetName(navController, viewModel)
                        }

                        ProgressStep.PetBreed -> createPetScafflold(
                            navController,
                            progressStep,
                            true
                        ) {
                            selectPetBreed(navController, viewModel)
                        }

                        ProgressStep.PetBirthday -> createPetScafflold(
                            navController,
                            progressStep,
                            true
                        ) {
                            selectPetBirthday(navController, viewModel)
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}