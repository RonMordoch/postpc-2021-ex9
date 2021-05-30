package exercises.android.ronm.shoenotifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import exercises.android.ronm.shoenotifications.onboarding.OnboardingActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appContext = applicationContext as ShoeNotificationsApp

        // init onboarding process if user did not finish it yet, else start the post-onboarding activity
        if (appContext.onboardingDone) {
            val postOnboardingActivityIntent = Intent(this@MainActivity, PostOnboardingActivity::class.java)
            startActivity(postOnboardingActivityIntent)
        } else {
            val onboardingActivityIntent = Intent(this@MainActivity, OnboardingActivity::class.java)
            startActivity(onboardingActivityIntent)
        }
        this.finish()
    }
}