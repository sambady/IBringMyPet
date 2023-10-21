package pammi.ibringmypet

import androidx.compose.runtime.mutableStateOf

class Pet(val petType : String, val petName : String, val petBreed : String)




object PetManager
{
    val pets = mutableStateOf(mutableListOf<Pet>())
}