package exercises.android.ronm.shoenotifications

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import exercises.android.ronm.shoenotifications.onboarding.age.AgeFragment
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AgeFragmentTest {

    private lateinit var scenario: FragmentScenario<AgeFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ShoeNotifications)
        scenario.moveToState(Lifecycle.State.STARTED)

    }

    @Test
    fun whenEmptyAgeInput_then_fabShouldBeDisabled() {
        onView(withId(R.id.fabAgeDone)).check(matches(not(isEnabled())))

    }

    @Test
    fun whenInvalidAgeInput_then_fabShouldBeDisabled() {
        onView(withId(R.id.editTextInputAge)).perform(typeText(INVALID_AGE))
        onView(withId(R.id.fabAgeDone)).check(matches(not(isEnabled())))
    }


    @Test
    fun whenValidAgeInput_then_fabShouldBeEnabled_and_Clickable() {
        onView(withId(R.id.editTextInputAge)).perform(typeText(VALID_AGE))
        onView(withId(R.id.fabAgeDone)).check(matches(isEnabled()))
    }

    @Test
    fun test_AgeTextFieldHint() {
        onView(withId(R.id.textFieldAge)).check(matches(hasTextInputLayoutHintText(VALID_AGE_HINT)))
    }

    @Test
    fun test_AgeTextFieldError_when_InvalidInputIsTyped_withValidErrorMsg() {
        onView(withId(R.id.editTextInputAge)).perform(typeText(INVALID_AGE))
        onView(withId(R.id.textFieldAge)).check(matches(hasTextInputLayoutErrorText(AGE_ERROR_MSG_VALID)))
    }

    @Test
    fun test_AgeTextFieldError_when_InvalidInputIsTyped_withInvalidErrorMsg() {
        onView(withId(R.id.editTextInputAge)).perform(typeText(INVALID_AGE))
        onView(withId(R.id.textFieldAge)).check(matches(not(hasTextInputLayoutErrorText(AGE_ERROR_MSG_INVALID))))
    }


    // Private custom matchers for Material Design's TextInputLayout

    // hint matcher
    private fun hasTextInputLayoutHintText(expectedHintText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {}

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            val error = item.hint ?: return false
            val hint = error.toString()
            return expectedHintText == hint
        }
    }

    // error matcher
    private fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {}

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            val error = item.error ?: return false
            val hint = error.toString()
            return expectedErrorText == hint
        }
    }

    companion object {
        private const val INVALID_AGE = "17"
        private const val VALID_AGE = "18"
        private const val VALID_AGE_HINT = "Age"
        private const val AGE_ERROR_MSG_VALID = "Please enter a valid age (18+)!"
        private const val AGE_ERROR_MSG_INVALID = "get lost!"
    }


}