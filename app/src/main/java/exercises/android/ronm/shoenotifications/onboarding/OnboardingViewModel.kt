package exercises.android.ronm.shoenotifications.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {

    val progressLiveData = MutableLiveData(MIN_PROGRESS)
    val onboardingDoneLiveData = MutableLiveData(false)

    fun increaseProgress() {
        if (progressLiveData.value!! < MAX_PROGRESS) {
            progressLiveData.value = progressLiveData.value?.plus(1)
            onboardingDoneLiveData.value = progressLiveData.value == MAX_PROGRESS
        }

    }

    fun decreaseProgress() {
        if (progressLiveData.value!! > MIN_PROGRESS) {
            progressLiveData.value = progressLiveData.value?.minus(1)
            onboardingDoneLiveData.value = progressLiveData.value == MAX_PROGRESS
        }
    }

    companion object {
        private const val MIN_PROGRESS = 0
        private const val MAX_PROGRESS = 5

    }
}

