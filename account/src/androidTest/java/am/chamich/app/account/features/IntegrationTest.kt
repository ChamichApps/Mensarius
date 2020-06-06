package am.chamich.app.account.features

import am.chamich.app.account.AccountTestHelper
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class IntegrationTest : AccountTestHelper() {

    @Rule
    lateinit var activityTestRule: ActivityTestRule<AccountsActivity>

    @Before
    fun setup() {
        activityTestRule = ActivityTestRule(AccountsActivity::class.java)
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun when_UserAddsAccounts_then_AccountsAppearsInTheList() {
        // Adding Accounts
        repeat(2) { number ->
            clickAddAccount()
            fillAccountForm(createAccount(number))
            clickSaveAccount()
        }

        // Verifying Accounts were added to the Recycler View
        verifyAccountsInRecyclerView(listOf(USER_NUMBER_FIRST, USER_NUMBER_SECOND))

        // Removing added Accounts
        repeat(2) {
            clickOnRecyclerViewItem(0)
            clickDelete()
        }
    }

    @Test
    fun when_UserEditsAccount_then_AccountInformationUpdatesInTheList() {
        // Adding Account
        clickAddAccount()
        fillAccountForm(createAccount(USER_NUMBER_FIRST))
        clickSaveAccount()

        // Verify Account Data in List is Correct
        verifyAccountsInRecyclerView(listOf(USER_NUMBER_FIRST))

        // Verifying Account Data
        clickOnRecyclerViewItem(0)
        verifyAccountForm(USER_NUMBER_FIRST)

        // Editing Account Data
        fillAccountForm(createAccount(USER_NUMBER_SECOND), true)
        clickSaveAccount()

        // Verifying Account Data was changed
        verifyAccountsInRecyclerView(listOf(USER_NUMBER_SECOND))

        // Removing added Account
        clickOnRecyclerViewItem(0)
        clickDelete()
    }

    companion object {
        const val USER_NUMBER_FIRST = 0
        const val USER_NUMBER_SECOND = 1
    }
}