package exercises.android.ronm.shoenotifications.onboarding.math

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

class MathQuestionFragment : Fragment(R.layout.fragment_math_question) {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val mathViewModel: MathViewModel by viewModels()
    private lateinit var textViewMathQuestion: TextView
    private lateinit var textFieldMathAnswer: TextInputLayout
    private lateinit var fabMathDone: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewMathQuestion = view.findViewById(R.id.textViewMathQuestion)
        textFieldMathAnswer = view.findViewById(R.id.outlinedTextFieldMathAnswer)
        fabMathDone = view.findViewById(R.id.fabMathDone)

        // set initial states according to view-model
        textViewMathQuestion.text = mathViewModel.question.toString()
        textFieldMathAnswer.editText?.setText(mathViewModel.answerInputString)
        fabMathDone.isEnabled = mathViewModel.answerCorrectLiveData.value ?: false

        // set listener to answer text field
        textFieldMathAnswer.editText?.doOnTextChanged { inputText, _, _, _ ->
            mathViewModel.answerInputString = inputText.toString()
        }

        // set an observer to enable button upon correct answer
        setMathAnswerObserver()

        // set on-click listener for the fab to navigate forward
        fabMathDone.setOnClickListener {
            fabMathDoneOnClick()
        }

    }

    private fun setMathAnswerObserver() {
        val answerCorrectObserver = Observer<Boolean?> { isAnswerCorrect ->
            if (isAnswerCorrect == null) {
                textFieldMathAnswer.error = ""
                fabMathDone.isEnabled = false
                return@Observer
            }
            fabMathDone.isEnabled = isAnswerCorrect
            textFieldMathAnswer.error = if (isAnswerCorrect) "" else getString(R.string.math_field_incorrect_answer)
        }
        mathViewModel.answerCorrectLiveData.observe(viewLifecycleOwner, answerCorrectObserver)
    }

    private fun fabMathDoneOnClick() {
        onboardingViewModel.increaseProgress()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_mathQuestionFragment_to_nameFragment)
    }


}