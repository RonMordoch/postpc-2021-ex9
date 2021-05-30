package exercises.android.ronm.shoenotifications.onboarding.name

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {

    // a live data for the first name field
    val firstNameLiveData = MutableLiveData<Boolean?>(null)
    var firstNameUserInput = ""
        set(value) {
            if (value == "") { // empty input, don't act upon it
                firstNameLiveData.value = null
                return
            }
            if (isNameValid(value)){
                field = value
                firstNameLiveData.value = true
            }
            else
            {
                firstNameLiveData.value = false
            }
            if (lastNameLiveData.value != null){
                nameValidLiveData.value = (firstNameLiveData.value == true) && (lastNameLiveData.value == true)
            }
        }

    // a live data for the last name field
    val lastNameLiveData = MutableLiveData<Boolean?>(null)
    var lastNameUserInput = ""
        set(value) {
            if (value == "") { // empty input, don't act upon it
                lastNameLiveData.value = null
                return
            }
            if (isNameValid(value)){
                lastNameLiveData.value = true
                field = value
            }
            else
            {
                lastNameLiveData.value = false
            }
            if (firstNameLiveData.value != null){
                nameValidLiveData.value = (firstNameLiveData.value == true) && (lastNameLiveData.value == true)
            }
        }

    // a live data for both names
    val nameValidLiveData = MutableLiveData<Boolean>(false)

    private fun isNameValid(name: String): Boolean {
        return (name.length >= MIN_NAME_LENGTH) && (name.all { it.isLetter() })
    }

    companion object {
        private const val MIN_NAME_LENGTH = 3
    }


}