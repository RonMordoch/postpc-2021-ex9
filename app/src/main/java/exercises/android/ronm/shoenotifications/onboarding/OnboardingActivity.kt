package exercises.android.ronm.shoenotifications.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import exercises.android.ronm.shoenotifications.R

class OnboardingActivity : AppCompatActivity() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()
    private lateinit var progressBarOnboarding: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        progressBarOnboarding = findViewById(R.id.progressBarOnboarding)

        val progressBarObserver = Observer<Int> { progressInt ->
            progressBarOnboarding.progress = progressInt
        }
        onboardingViewModel.progressLiveData.observe(this, progressBarObserver)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onboardingViewModel.decreaseProgress()
    }

}