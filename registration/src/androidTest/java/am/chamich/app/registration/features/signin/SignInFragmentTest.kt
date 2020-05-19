package am.chamich.app.registration.features.signin


import am.chamich.app.registration.Actions
import am.chamich.app.registration.Matchers
import am.chamich.app.registration.R
import am.chamich.app.registration.ViewModelFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    private val actions = Actions()
    private val matchers = Matchers()
    private val mockedViewModel: SignInViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        launchFragment()
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        verify(exactly = 1) { mockedViewModel.signIn(VALID_EMAIL, VALID_PASSWORD) }

        confirmVerified()
    }

    @Test
    fun when_ValidPasswordProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)
        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        verify(exactly = 1) { mockedViewModel.signIn(VALID_EMAIL, VALID_PASSWORD) }

        confirmVerified()
    }

    @Test
    fun when_InvalidEmailAndPasswordProvided_then_ErrorMessageIsShownForBothFields() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)

        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)
        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        verify(exactly = 0) { mockedViewModel.signIn(any(), any()) }

        confirmVerified()
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

    companion object {
        const val INVALID_EMAIL = "chamich.apps@gmail"
        const val VALID_EMAIL = "chamich.apps@gmail.com"
        const val INVALID_PASSWORD = "test"
        const val VALID_PASSWORD = "Test1234!"
    }
}