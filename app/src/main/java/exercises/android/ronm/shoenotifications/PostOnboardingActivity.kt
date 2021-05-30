package exercises.android.ronm.shoenotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import exercises.android.ronm.shoenotifications.onboarding.name.NameViewModel

class PostOnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_onboarding)
    }
}