package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.helpers.Actions
import am.chamich.app.registration.helpers.Matchers
import am.chamich.app.registration.helpers.Mocks
import am.chamich.app.registration.helpers.ViewModelFactory
import am.chamich.app.registration.model.User
import am.chamich.app.registration.navigation.api.INavigator
import android.app.Activity.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    private val actions = Actions()
    private val matchers = Matchers()
    private val mocks = Mocks()

    private val signedInUserLiveData = MutableLiveData<User>()

    private val mockedUser: User = mocks.mock()
    private val mockedNavigator: INavigator = mocks.mock()
    private val mockedViewModel: SignInViewModel = mocks.mock {
        mocks.every { signedInUser } returns signedInUserLiveData
    }

    @Before
    fun setup() {
        launchFragment()

        mocks.verify(times = 1) { mockedViewModel.signedInUser }
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.performClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.performClick(R.id.button_sign_in)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        mocks.verify(1) { mockedViewModel.signIn(VALID_EMAIL, VALID_PASSWORD) }
    }

    @Test
    fun when_ValidPasswordProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)
        actions.performClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.performClick(R.id.button_sign_in)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        mocks.verify(1) { mockedViewModel.signIn(VALID_EMAIL, VALID_PASSWORD) }
    }

    @Test
    fun when_InvalidEmailAndPasswordProvided_then_ErrorMessageIsShownForBothFields() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)

        actions.performClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)
        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        mocks.verify(0) { mockedViewModel.signIn(any(), any()) }
    }

    @Test
    fun when_UserEntersValidCredentials_then_ActivityFinishedAndUserDataSetAsResult() {
        mocks.every { mockedViewModel.signIn(any(), any()) } answers {
            signedInUserLiveData.postValue(mockedUser)
        }

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)

        actions.performClick(R.id.button_sign_in)

        mocks.coVerify(times = 1) { mockedNavigator.finishActivityWithResult(RESULT_OK) }
    }

    private fun launchFragment(): FragmentScenario<SignInFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return SignInFragment().apply {
                    viewModelFactory = ViewModelFactory(mockedViewModel)
                    navigator = mockedNavigator
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