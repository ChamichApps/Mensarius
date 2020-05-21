package am.chamich.app.registration.features.password

import am.chamich.app.registration.R
import am.chamich.app.registration.helpers.Actions
import am.chamich.app.registration.helpers.Matchers
import am.chamich.app.registration.helpers.Mocks
import am.chamich.app.registration.helpers.ViewModelFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.Before
import org.junit.Test

class RestorePasswordFragmentTest {

    private val actions = Actions()
    private val matchers = Matchers()
    private val mocks = Mocks()

    private val mockedViewModel: RestorePasswordViewModel = mocks.mock()

    @Before
    fun setup() {
        launchFragment()
    }

    @Test
    fun when_ValidEmailProvided_then_ErrorMessageIsDisappearing() {
        actions.enterText(R.id.edit_text_email, INVALID_EMAIL)
        actions.performClick(R.id.button_restore_password)

        matchers.viewIsDisplayedAndContainsText(R.string.error_email_invalid)

        actions.enterText(R.id.edit_text_email, VALID_EMAIL)
        actions.performClick(R.id.button_restore_password)

        matchers.viewWithTextIsNotDisplayed(R.string.error_email_invalid)

        mocks.verify(times = 1) { mockedViewModel.restorePassword(VALID_EMAIL) }
    }

    private fun launchFragment(): FragmentScenario<RestorePasswordFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return RestorePasswordFragment().apply {
                    viewModelFactory =
                        ViewModelFactory(
                            mockedViewModel
                        )
                }
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light)
    }

    companion object {
        const val INVALID_EMAIL = "chamich.apps@gmail"
        const val VALID_EMAIL = "chamich.apps@gmail.com"
    }
}