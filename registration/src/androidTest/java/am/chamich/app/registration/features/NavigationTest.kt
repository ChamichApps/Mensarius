package am.chamich.app.registration.features

import am.chamich.app.registration.Actions
import am.chamich.app.registration.Matchers
import am.chamich.app.registration.R
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

/**
 * Test checks that navigation through the flow works.
 */
class NavigationTest {

    private val actions = Actions()
    private val matchers = Matchers()

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

        actions.perfoemClick(R.id.button_forgot_password)

        matchers.viewIsDisplayedAndContainsText(R.string.text_button_restore)
    }

    @Test
    fun navigateToSignInFromSignUp() {
        navigateToSignInWithVerification()
        navigateToSignUpWithVerification()
    }

    private fun navigateToSignUpWithVerification() {
        actions.perfoemClick(R.id.button_sign_up)

        matchers.viewIsDisplayedAndContainsText(R.string.text_button_create_account)
        matchers.viewIsDisplayedAndContainsText(R.string.text_have_an_account_sign_in)
    }

    private fun navigateToSignInWithVerification() {
        actions.perfoemClick(R.id.button_sign_in)

        matchers.viewIsDisplayedAndContainsText(R.string.text_button_sign_in)
        matchers.viewIsDisplayedAndContainsText(R.string.text_dont_have_an_account_sign_up)
        matchers.viewIsDisplayedAndContainsText(R.string.text_button_forgot_password)
    }
}