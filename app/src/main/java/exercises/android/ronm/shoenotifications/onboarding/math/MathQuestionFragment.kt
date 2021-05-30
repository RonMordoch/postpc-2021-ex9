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

class MathQuestionFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val mathViewModel: MathViewModel by viewModels()
    private lateinit var textViewMathQuestion: TextView
    private lateinit var textFieldMathAnswer: TextInputLayout
    private lateinit var fabMathQuestionDone: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_math_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewMathQuestion = view.findViewById(R.id.textViewMathQuestion)
        textFieldMathAnswer = view.findViewById(R.id.outlinedTextFieldMathAnswer)
        fabMathQuestionDone = view.findViewById(R.id.fabMathQuestionDone)


        // generate a question from the view-model
        textViewMathQuestion.text = mathViewModel.question.toString()

        // set listener to answer text field
        textFieldMathAnswer.editText?.doOnTextChanged { inputText, _, _, _ ->
            mathViewModel.answerInputString = inputText.toString()
        }

        // set an observer to enable button upon correct answer
        val answerCorrectObserver = Observer<Boolean?> { isAnswerCorrect ->
            if (isAnswerCorrect == null) {
                textFieldMathAnswer.error = ""
                fabMathQuestionDone.isEnabled = false
                return@Observer
            }
            fabMathQuestionDone.isEnabled = isAnswerCorrect
            if (isAnswerCorrect) {
                textFieldMathAnswer.error = ""
            } else {
                textFieldMathAnswer.error = "Incorrect! Try again."
            }

        }
        mathViewModel.answerCorrectLiveData.observe(viewLifecycleOwner, answerCorrectObserver)

        // set on-click listener for the fab to navigate forward
        fabMathQuestionDone.setOnClickListener {
            onboardingViewModel.increaseProgress()
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_mathQuestionFragment_to_nameFragment)
        }


    }


}