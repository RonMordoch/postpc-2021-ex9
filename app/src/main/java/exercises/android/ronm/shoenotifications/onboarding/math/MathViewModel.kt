package exercises.android.ronm.shoenotifications.onboarding.math

import androidx.lifecycle.ViewModel

class MathViewModel : ViewModel() {


    fun generateRandomQuestion() : MathQuestion{
        val lhs = (0..10).random()
        val rhs = (0..10).random()
        val op = Operators.values().random()
        return MathQuestion(lhs, rhs, op)
        // todo check we don't return the same question ( probability of that is 1/(11*11*3) ~= 0.002)
    }
}