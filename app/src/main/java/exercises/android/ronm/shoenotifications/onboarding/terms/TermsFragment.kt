package exercises.android.ronm.shoenotifications.onboarding.terms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel


class TermsFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_terms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // disable button on init
        val fabAgreeToTerms: FloatingActionButton = view.findViewById(R.id.fabAgreeToTerms)
        fabAgreeToTerms.isEnabled = false

        // enable button only when user has reached the end of terms
        val scrollViewTermsText: ScrollView = view.findViewById(R.id.scrollViewTermsText)
        scrollViewTermsText.setOnScrollChangeListener { _, _, _, _, _ ->
            // only run when button is disabled so it won't disable when user scrolls all the way down and then scrolls a bit up
            if (!fabAgreeToTerms.isEnabled) {
                val viewBottom = scrollViewTermsText.getChildAt(scrollViewTermsText.childCount - 1);
                val diff = (viewBottom.bottom - (scrollViewTermsText.height + scrollViewTermsText.scrollY));
                fabAgreeToTerms.isEnabled = (diff == 0)  // if diff is zero, then the bottom has been reached}
            }
        }

        fabAgreeToTerms.setOnClickListener {
            onboardingViewModel.increaseProgress()
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_termsFragment_to_mathQuestionFragment)
        }
    }


}
