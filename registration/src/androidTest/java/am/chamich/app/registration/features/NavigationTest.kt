package am.chamich.app.registration.features

import am.chamich.app.registration.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

/**
 * Test checks that navigation through the flow works.
 */
class NavigationTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(RegistrationActivity::class.java)

    @Test
    fun navigateToSignIn() {
        navigateToSignInWithVerification()
    }

    @Test
    fun navigateToSignUp() {
        navigateToSignIn()
        navigateToSignUpWithVerification()
    }

    @Test
    fun navigateToRestorePassword() {
        navigateToSignInWithVerification()

        onView(withId(R.id.button_forgot_password)).perform(click())

        onView(withId(R.id.button_restore_password)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToSignInFromSignUp() {
        navigateToSignInWithVerification()
        navigateToSignUpWithVerification()
    }

    private fun navigateToSignUpWithVerification() {
        onView(withId(R.id.button_sign_up)).perform(click())

        onView(withId(R.id.button_create_account)).check(matches(isDisplayed()))
        onView(withId(R.id.button_sign_in)).check(matches(isDisplayed()))
    }

    private fun navigateToSignInWithVerification() {
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(R.id.button_sign_in)).check(matches(isDisplayed()))
        onView(withId(R.id.button_sign_up)).check(matches(isDisplayed()))
        onView(withId(R.id.button_forgot_password)).check(matches(isDisplayed()))
    }
}