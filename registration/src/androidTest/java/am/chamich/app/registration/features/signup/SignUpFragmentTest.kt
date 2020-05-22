package am.chamich.app.registration.features.signup


import am.chamich.app.registration.R
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.features.RESULT_SIGN_UP_SUCCESS
import am.chamich.app.registration.helpers.*
import am.chamich.app.registration.model.User
import am.chamich.app.registration.navigation.api.INavigator
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpFragmentTest {

    private val actions = Actions()
    private val matchers = Matchers()
    private val mocks = Mocks()
    private val assertions = Assertions()

    private val signedUpUserLiveData = MutableLiveData<User>()
    private val failureLiveData = MutableLiveData<Failure>()

    private val mockedUser: User = mocks.mock {
        mocks.every { id } returns USER_ID
    }
    private val mockedNavigator: INavigator = mocks.mock()
    private val mockedViewModel: SignUpViewModel = mocks.mock {
        mocks.every { signedUpUser } returns signedUpUserLiveData
        mocks.every { signUpFailure } returns failureLiveData
    }

    @Before
    fun setup() {
        launchFragment()

        mocks.verify(times = 1) { mockedViewModel.signedUpUser }
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.performClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.performClick(R.id.button_create_account)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        mocks.verify(times = 1) { mockedViewModel.signUp(VALID_EMAIL, VALID_PASSWORD) }
    }

    @Test
    fun when_ValidPasswordProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)
        actions.performClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)
        actions.performClick(R.id.button_create_account)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)
        matchers.viewWithTextIsNotDisplayed(R.string.error_password_invalid)

        mocks.verify(times = 1) { mockedViewModel.signUp(VALID_EMAIL, VALID_PASSWORD) }
    }

    @Test
    fun when_InvalidEmailAndPasswordProvided_then_ErrorMessageIsShownForBothFields() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.enterText(R.id.edit_text_password, INVALID_PASSWORD)

        actions.performClick(R.id.button_create_account)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)
        matchers.viewIsDisplayedAndContainsText(R.string.error_password_invalid)

        mocks.verify(times = 0) { mockedViewModel.signUp(any(), any()) }
    }

    @Test
    fun when_SignUpSuccessful_then_ActivityFinishedAndUserDataSetAsResult() {
        mocks.every { mockedViewModel.signUp(any(), any()) } answers {
            signedUpUserLiveData.postValue(mockedUser)
        }

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)

        actions.performClick(R.id.button_create_account)

        val slot = mocks.slot<Intent>()
        mocks.coVerify(times = 1) {
            mockedNavigator.finishActivityWithResult(RESULT_SIGN_UP_SUCCESS, capture(slot))
        }

        assertions.assertThat(slot.captured.getIntExtra(EXTRA_USER_ID, 0), `is`(USER_ID))
    }

    @Test
    fun when_ServerGeneratesError_then_UserInformed() {
        mocks.every { mockedViewModel.signUp(any(), any()) } answers {
            failureLiveData.value = Failure.ServerError
        }

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.enterText(R.id.edit_text_password, VALID_PASSWORD)

        actions.performClick(R.id.button_create_account)

        matchers.toastWithMessageIsDisplayed(R.string.error_sign_up_failed)
    }

    private fun launchFragment(): FragmentScenario<SignUpFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return SignUpFragment().apply {
                    viewModelFactory = ViewModelFactory(mockedViewModel)
                    navigator = mockedNavigator
                }
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light)
    }

    companion object {
        const val USER_ID = 28343
        const val INVALID_EMAIL = "chamich.apps@gmail"
        const val VALID_EMAIL = "chamich.apps@gmail.com"
        const val INVALID_PASSWORD = "test"
        const val VALID_PASSWORD = "Test1234!"
    }
}