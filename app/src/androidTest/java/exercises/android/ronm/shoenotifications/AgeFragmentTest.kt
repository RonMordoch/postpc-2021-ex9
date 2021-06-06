//package exercises.android.ronm.shoenotifications
//
//import androidx.fragment.app.testing.launchFragmentInContainer
//import androidx.navigation.NavController
//import androidx.navigation.Navigation
//import androidx.navigation.testing.TestNavHostController
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.action.ViewActions.replaceText
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import exercises.android.ronm.shoenotifications.onboarding.age.AgeFragment
//import exercises.android.ronm.shoenotifications.onboarding.hello.HelloFragment
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//
//
//@RunWith(AndroidJUnit4::class)
//class AgeFragmentTest {
//
//    @Test
//    fun testAgeFragmentNav(){
////        val scenario =  launchFragmentInContainer<AgeFragment>()
////        // create a mock NavController
////        val navController = mock(NavController::class.java)
////        // make our new mock the fragment's NavController
////        scenario.onFragment {
////            Navigation.setViewNavController(it.view!!, navController)
////        }
////        onView(withId(R.id.outlinedTextFieldAge)).perform(replaceText("25"))
////        onView(withId(R.id.fabAgeDone)).perform(click())
////        verify(navController).navigate(R.id.action_ageFragment_to_termsFragment)
//
//
//        val navController = TestNavHostController(
//            ApplicationProvider.getApplicationContext())
//        val titleScenario = launchFragmentInContainer<HelloFragment>()
//        titleScenario.onFragment { fragment ->
//            // Set the graph on the TestNavHostController
//            navController.setGraph(R.navigation.navigation_graph)
//            Navigation.setViewNavController(fragment.requireView(), navController)
//            onView(withId(R.id.fabStartOnboarding)).perform(click())
//            assertThat(navController.currentDestination?.id).isEqualTo(R.id.ageFragment)
//
//        }
//
//
//
//
//
//    }
//}