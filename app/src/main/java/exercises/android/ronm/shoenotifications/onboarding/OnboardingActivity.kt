package exercises.android.ronm.shoenotifications.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import exercises.android.ronm.shoenotifications.PostOnboardingActivity
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.ShoeNotificationsApp

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

        val onboardingDoneObserver = Observer<Boolean>{ onboardingDone ->
            if (onboardingDone){
                (applicationContext as ShoeNotificationsApp).onboardingDone = true
                // kill this activity and start the post-onboarding one
                val postOnboardingActivityIntent = Intent(this@OnboardingActivity, PostOnboardingActivity::class.java)
                startActivity(postOnboardingActivityIntent)
                this.finish()
            }
        }
        onboardingViewModel.onboardingDoneLiveData.observe(this, onboardingDoneObserver)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onboardingViewModel.decreaseProgress()
    }

}