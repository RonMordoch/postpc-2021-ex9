package exercises.android.ronm.shoenotifications.onboarding.age

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel


class AgeFragment : Fragment() {

    private lateinit var textFieldAge: TextInputLayout
    private lateinit var fabAgeDone: FloatingActionButton
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val ageViewModel : AgeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textFieldAge = view.findViewById(R.id.outlinedTextFieldAge)
        fabAgeDone = view.findViewById(R.id.fabAgeDone)

        textFieldAge.editText?.doOnTextChanged { inputText, _, _, _ ->
            ageViewModel.ageInputString = inputText.toString()
        }

        // set an observer to enable button upon valid age input
        val ageValidObserver = Observer<Boolean?>{ isAgeValid ->
            if (isAgeValid == null){ // disables button immediately when view is created in the first time
                textFieldAge.error = ""
                fabAgeDone.isEnabled = false
                return@Observer
            }
            fabAgeDone.isEnabled = isAgeValid
            if (!isAgeValid){
                textFieldAge.error = "Please enter a valid age (18+)!"
                fabAgeDone.isEnabled = false
            }
            else
            {
                textFieldAge.error = ""
                fabAgeDone.isEnabled = true
            }

        }
        ageViewModel.ageValidLiveData.observe(viewLifecycleOwner, ageValidObserver)

        // set on-click listener for the fab to navigate forward
        fabAgeDone.setOnClickListener{
            onboardingViewModel.increaseProgress()
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_ageFragment_to_termsFragment)
        }
}

}