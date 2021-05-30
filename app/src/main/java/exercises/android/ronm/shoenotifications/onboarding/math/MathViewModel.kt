package exercises.android.ronm.shoenotifications.onboarding.math

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MathViewModel : ViewModel() {

    val answerCorrectLiveData = MutableLiveData<Boolean?>(null)
    var answerInputString: String = ""
    set(value) {
        if (value == "") {
            answerCorrectLiveData.value = null
            return
        }
        field = value
        try {
            val answerInputInt = value.toInt()
            answerCorrectLiveData.value = (answerInputInt == question.result)
        } catch (e: Exception) {
            answerCorrectLiveData.value = false
        }
    }

    lateinit var question : MathQuestion
    init {
        generateRandomQuestion()
    }


    private fun generateRandomQuestion() {
        // probability of same question is negligible for our purposes (~ 0.003)
        val lhs = (0..9).random()
        val rhs = (0..9).random()
        val op = Operators.values().random()
        question =  MathQuestion(lhs, rhs, op)
    }




}