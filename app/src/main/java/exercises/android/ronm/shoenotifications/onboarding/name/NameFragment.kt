package exercises.android.ronm.shoenotifications.onboarding.name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel
import exercises.android.ronm.shoenotifications.onboarding.math.MathViewModel


class NameFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val nameViewModel : NameViewModel by viewModels()
    private lateinit var textFieldFirstName : TextInputLayout
    private lateinit var textFieldLastName : TextInputLayout
    private lateinit var fabNameDone : FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textFieldFirstName = view.findViewById(R.id.textInputFirstName)
        textFieldLastName = view.findViewById(R.id.textInputLastName)
        fabNameDone = view.findViewById(R.id.fabNameDone)

        // set listener to first name text field
        textFieldFirstName.editText?.doOnTextChanged { inputText, _, _, _ ->
            nameViewModel.firstNameUserInput = inputText.toString()
        }
        // set an observer for first name field
        val firstNameValidObserver = Observer<Boolean?>{ isFirstNameValid ->
            if (isFirstNameValid == null){
                textFieldFirstName.error = ""
                return@Observer
            }
            textFieldFirstName.error = if (isFirstNameValid) "" else "Name must be alphabetic only and at least 3 letters!"
        }
        nameViewModel.firstNameLiveData.observe(viewLifecycleOwner, firstNameValidObserver)

        // set listener to last name text field
        textFieldLastName.editText?.doOnTextChanged { inputText, _, _, _ ->
            nameViewModel.lastNameUserInput = inputText.toString()
        }
        // set an observer for name name field
        val lastNameValidObserver = Observer<Boolean?>{ islastNameValid ->
            if (islastNameValid == null){
                textFieldLastName.error = ""
                return@Observer
            }
            textFieldLastName.error = if (islastNameValid) "" else "Name must be alphabetic only and at least 3 letters!"
        }
        nameViewModel.lastNameLiveData.observe(viewLifecycleOwner, lastNameValidObserver)


        // set an observer for both fields to enable / disable button
        val nameValidObserver = Observer<Boolean>{isNameValid ->
            fabNameDone.isEnabled = isNameValid
        }
        nameViewModel.nameValidLiveData.observe(viewLifecycleOwner, nameValidObserver)


        // set on-click listener for the fab to navigate forward
        fabNameDone.setOnClickListener{
            onboardingViewModel.increaseProgress()
            // finish !
        }
    }


}