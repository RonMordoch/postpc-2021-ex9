package exercises.android.ronm.shoenotifications

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import exercises.android.ronm.shoenotifications.onboarding.hello.HelloFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HelloFragmentTest {

    private lateinit var  scenario : FragmentScenario<HelloFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId =  R.style.Theme_ShoeNotifications)
        scenario.moveToState(Lifecycle.State.STARTED)

    }

    @Test
    fun testFabStartOnboardingIsClickable(){
        onView(withId(R.id.fabStartOnboarding)).check(matches(isClickable()))
    }


    @Test
    fun testFabStartOnboardingIsEnabled(){
        onView(withId(R.id.fabStartOnboarding)).check(matches(isEnabled()))
    }


}