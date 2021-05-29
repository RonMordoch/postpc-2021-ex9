package exercises.android.ronm.shoenotifications.onboarding.math

enum class Operators { PLUS, MINUS, MUL }

data class MathQuestion(val lhs: Int, val rhs: Int, val op: Operators) {

    var result: Int = 0
    private val opStringMap = mutableMapOf(Operators.PLUS to "+", Operators.MINUS to "-", Operators.MUL to "*")

    init {
        result = when (op) {
            Operators.PLUS -> {
                lhs + rhs
            }
            Operators.MINUS -> {
                lhs - rhs
            }
            Operators.MUL -> {
                lhs * rhs
            }
        }
    }

    override fun toString(): String {
        return "$lhs${opStringMap[op]}$rhs=?"
    }

}