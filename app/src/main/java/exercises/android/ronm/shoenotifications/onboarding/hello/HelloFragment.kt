package exercises.android.ronm.shoenotifications.onboarding.hello

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel


class HelloFragment : Fragment(R.layout.fragment_hello) {

    private lateinit var fabStartOnboarding: FloatingActionButton
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabStartOnboarding = view.findViewById(R.id.fabStartOnboarding)
        fabStartOnboarding.setOnClickListener {
            fabStartOnboardingOnClick()
        }
    }

    private fun fabStartOnboardingOnClick() {
        onboardingViewModel.increaseProgress()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_helloFragment_to_ageFragment)
    }


}

