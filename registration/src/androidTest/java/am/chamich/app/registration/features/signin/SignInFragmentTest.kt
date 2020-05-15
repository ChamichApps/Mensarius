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
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matchers.not
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
    fun when_IncorrectEmailProvided_then_ErrorMessageIsShown() {
        enterIncorrectEmailWithVerification()
    }

    @Test
    fun when_CorrectEmailProvided_then_ErrorMessageIsDisappearing() {
        enterIncorrectEmailWithVerification()
        enterCorrectEmailWithVerification()
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

    private fun enterIncorrectEmailWithVerification() {
        onView(withId(R.id.edit_text_email)).perform(replaceText(INCORRECT_EMAIL))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(withText(R.string.error_email_not_valid)))

        verify(exactly = 0) { mockedViewModel.signIn(any(), any()) }
    }

    private fun enterCorrectEmailWithVerification() {
        onView(withId(R.id.edit_text_email)).perform(replaceText(CORRECT_EMAIL))
        onView(withId(R.id.button_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.textinput_error))
            .check(matches(not(isDisplayed())))

        verify(exactly = 0) { mockedViewModel.signIn(any(), any()) }
    }

    companion object {
        const val INCORRECT_EMAIL = "chamich.apps@gmail"
        const val CORRECT_EMAIL = "chamich.apps@gmail.com"
    }
}