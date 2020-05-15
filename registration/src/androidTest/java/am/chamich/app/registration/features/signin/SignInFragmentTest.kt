package am.chamich.app.registration.features.signin


import am.chamich.app.registration.R
import am.chamich.app.registration.ViewModelFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    private val mockedViewModel: SignInViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        launchFragment()
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        enterInvalidDataWithVerification(
            INVALID_EMAIL, VALID_PASSWORD, R.string.error_email_invalid
        )
        enterValidDataWithVerification()
    }

    @Test
    fun when_ValidPasswordProvided_then_ErrorMessageIsDisappearing() {
        enterInvalidDataWithVerification(
            VALID_EMAIL, INVALID_PASSWORD, R.string.error_password_invalid
        )
        enterValidDataWithVerification()
    }

    private fun launchFragment(): FragmentScenario<SignInFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return SignInFragment().apply {
                    viewModelFactory = ViewModelFactory(mockedViewModel)
                }
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light)
    }

    private fun enterInvalidDataWithVerification(
        email: String,
        password: String,
        errorTextId: Int
    ) {
        onView(withId(R.id.edit_text_email)).perform(replaceText(email))
        onView(withId(R.id.edit_text_password)).perform(replaceText(password))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(withText(errorTextId)))

        verify(exactly = 0) { mockedViewModel.signIn(any(), any()) }

        confirmVerified(mockedViewModel)
    }

    private fun enterValidDataWithVerification() {
        onView(withId(R.id.edit_text_email)).perform(replaceText(VALID_EMAIL))
        onView(withId(R.id.edit_text_password)).perform(replaceText(VALID_PASSWORD))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error)).check(doesNotExist())

        verify(exactly = 1) { mockedViewModel.signIn(any(), any()) }

        confirmVerified(mockedViewModel)
    }

    companion object {
        const val INVALID_EMAIL = "chamich.apps@gmail"
        const val VALID_EMAIL = "chamich.apps@gmail.com"
        const val INVALID_PASSWORD = "test"
        const val VALID_PASSWORD = "Test1234!"
    }
}