package exercises.android.ronm.shoenotifications.onboarding.terms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel


class TermsFragment : Fragment(R.layout.fragment_terms) {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val termsViewModel: TermsViewModel by viewModels()
    private lateinit var fabAgreeToTerms: FloatingActionButton
    private lateinit var scrollViewTermsText: ScrollView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAgreeToTerms = view.findViewById(R.id.fabAgreeToTerms)
        // set initial fab state according to view-model
        fabAgreeToTerms.isEnabled = termsViewModel.termsAgreed

        // enable button only when user has reached the end of terms
        scrollViewTermsText = view.findViewById(R.id.scrollViewTermsText)
        scrollViewTermsText.setOnScrollChangeListener { _, _, _, _, _ ->
            setFabAgreeToTermsState()
        }

        fabAgreeToTerms.setOnClickListener {
            fabAgreeToTermsOnClick()
        }
    }

    private fun setFabAgreeToTermsState() {
        // only run when button is disabled so it won't disable when user scrolls all the way down and then scrolls a bit up
        if (!fabAgreeToTerms.isEnabled) {
            val viewBottom = scrollViewTermsText.getChildAt(scrollViewTermsText.childCount - 1);
            val diff = (viewBottom.bottom - (scrollViewTermsText.height + scrollViewTermsText.scrollY));
            fabAgreeToTerms.isEnabled = (diff == 0)  // if diff is zero, then the bottom has been reached
            termsViewModel.termsAgreed = (diff == 0) // update view model for future possible back-pressing
        }
    }

    private fun fabAgreeToTermsOnClick() {
        onboardingViewModel.increaseProgress()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_termsFragment_to_mathQuestionFragment)
    }


}
