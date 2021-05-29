package exercises.android.ronm.shoenotifications.onboarding.math

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import exercises.android.ronm.shoenotifications.R
import exercises.android.ronm.shoenotifications.onboarding.OnboardingViewModel

class MathQuestionFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val mathViewModel : MathViewModel by viewModels()
    private lateinit var textViewMathQuestion : TextView
    private lateinit var textFieldMathAnswer : TextInputLayout
    private lateinit var fabMathQuestionDone : FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_math_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewMathQuestion = view.findViewById(R.id.textViewMathQuestion)
        textFieldMathAnswer = view.findViewById(R.id.outlinedTextFieldMathAnswer)
        fabMathQuestionDone = view.findViewById(R.id.fabMathQuestionDone)

        // generate new question
        textViewMathQuestion.text = mathViewModel.generateRandomQuestion().toString()
    }



}