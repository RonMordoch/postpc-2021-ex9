package exercises.android.ronm.shoenotifications.onboarding.age

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgeViewModel : ViewModel() {

    val ageValidLiveData = MutableLiveData<Boolean?>(null)
    var ageInputString: String = ""
        set(value) {
            if (value == "") {
                ageValidLiveData.value = null
                return
            }
            field = value
            try {
                val ageInt = value.toInt()
                ageValidLiveData.value = (ageInt >= MIN_AGE)
            } catch (e: Exception) {
                ageValidLiveData.value = false
            }
        }

    companion object {
        private const val MIN_AGE = 18
    }
}