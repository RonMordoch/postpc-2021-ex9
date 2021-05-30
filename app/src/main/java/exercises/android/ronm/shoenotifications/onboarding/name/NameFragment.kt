package exercises.android.ronm.shoenotifications.onboarding.name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel


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


        // set initial states according to view-model
        textFieldFirstName.editText?.setText(nameViewModel.firstNameUserInput)
        textFieldLastName.editText?.setText(nameViewModel.lastNameUserInput)
        fabNameDone.isEnabled = nameViewModel.nameValidLiveData.value ?: false



        // set listener to first-name text field
        textFieldFirstName.editText?.doOnTextChanged { inputText, _, _, _ ->
            nameViewModel.firstNameUserInput = inputText.toString()
        }
        // set an observer for the first-name field
        setFirstNameObserver()

        // set listener to last-name text field
        textFieldLastName.editText?.doOnTextChanged { inputText, _, _, _ ->
            nameViewModel.lastNameUserInput = inputText.toString()
        }
        // set an observer for the last-name field
        setLastNameObserver()

        // set an observer for both fields to enable / disable button
        setUserNameValidObserver()


        // set on-click listener for the fab to navigate forward
        fabNameDone.setOnClickListener{
            onboardingViewModel.increaseProgress()
            // finish ! activity will take command from here
        }
    }

    private fun setUserNameValidObserver() {
        val nameValidObserver = Observer<Boolean> { isNameValid ->
            fabNameDone.isEnabled = isNameValid
        }
        nameViewModel.nameValidLiveData.observe(viewLifecycleOwner, nameValidObserver)
    }

    private fun setLastNameObserver() {
        val lastNameValidObserver = Observer<Boolean?> { isLastNameValid ->
            if (isLastNameValid == null) {
                textFieldLastName.error = ""
                return@Observer
            }
            textFieldLastName.error = if (isLastNameValid) "" else getString(R.string.name_field_invalid_name)
        }
        nameViewModel.lastNameLiveData.observe(viewLifecycleOwner, lastNameValidObserver)
    }

    private fun setFirstNameObserver() {
        val firstNameValidObserver = Observer<Boolean?> { isFirstNameValid ->
            if (isFirstNameValid == null) {
                textFieldFirstName.error = ""
                return@Observer
            }
            textFieldFirstName.error = if (isFirstNameValid) "" else getString(R.string.name_field_invalid_name)
        }
        nameViewModel.firstNameLiveData.observe(viewLifecycleOwner, firstNameValidObserver)
    }


}