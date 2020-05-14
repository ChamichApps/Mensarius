package am.chamich.app.registration.features.signin


import am.chamich.app.registration.R
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<SignInFragment>(
            null, R.style.Theme_MaterialComponents_Light, FragmentFactory()
        )
    }

    @Test
    fun verifyErrorShownWhenWrongEmailProvided() {
        enterWrongEmailAndVerify()
    }

    @Test
    fun verifyErrorDisappearingAfterProvidingCorrectEmail() {
        enterWrongEmailAndVerify()

        onView(withId(R.id.edit_text_email)).perform(replaceText(CORRECT_EMAIL))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(not(isDisplayed())))
    }

    private fun enterWrongEmailAndVerify() {
        onView(withId(R.id.edit_text_email)).perform(replaceText(INCORRECT_EMAIL))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(withText(R.string.error_email_not_valid)))
    }

    companion object {
        const val INCORRECT_EMAIL = "chamich.apps@gmail"
        const val CORRECT_EMAIL = "chamich.apps@gmail.com"
    }
}