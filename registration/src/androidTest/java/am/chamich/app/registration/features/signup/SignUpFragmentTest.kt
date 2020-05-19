package am.chamich.app.registration.features.signup


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
class SignUpFragmentTest {

    private val actions = Actions()
    private val matchers = Matchers()
    private val mockedViewModel: SignUpViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        launchFragment()
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.perfoemClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.perfoemClick(R.id.button_create_account)

        verify(exactly = 1) { mockedViewModel.signUp(VALID_EMAIL, VALID_PASSWORD) }

        confirmVerified()
    }

    @Test
    fun when_ValidPasswordProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)
        actions.perfoemClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.perfoemClick(R.id.button_create_account)

        verify(exactly = 1) { mockedViewModel.signUp(VALID_EMAIL, VALID_PASSWORD) }

        confirmVerified()
    }

    @Test
    fun when_InvalidEmailAndPasswordProvided_then_ErrorMessageIsShownForBothFields() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)

        actions.perfoemClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)
        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        verify(exactly = 0) { mockedViewModel.signUp(any(), any()) }

        confirmVerified()
    }

    private fun launchFragment(): FragmentScenario<SignUpFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return SignUpFragment().apply {
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