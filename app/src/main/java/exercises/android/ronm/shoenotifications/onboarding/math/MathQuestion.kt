package exercises.android.ronm.shoenotifications.onboarding.math

enum class Operators { PLUS, MUL }

/**A Simple data class representing a binary math equation with one operator. */
data class MathQuestion(val lhs: Int, val rhs: Int, val op: Operators) {

    var result: Int = 0
    private val opStringMap = mutableMapOf(Operators.PLUS to PLUS, Operators.MUL to MUL)

    init {
        result = when (op) {
            Operators.PLUS -> {
                lhs + rhs
            }
            Operators.MUL -> {
                lhs * rhs
            }
        }
    }

    override fun toString(): String {
        return "$lhs${opStringMap[op]}$rhs=?"
    }

    companion object{
        private const val PLUS = "+"
        private const val MUL = "*"
    }

}