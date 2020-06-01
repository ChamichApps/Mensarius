package am.chamich.app.account.features.accounts

import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.extensions.asStringResource
import am.chamich.app.account.helpers.Matchers
import am.chamich.app.account.helpers.ViewModelFactory
import am.chamich.app.account.models.AccountColor
import am.chamich.app.account.models.AccountType
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class AccountsFragmentTest {

    private val matchers = Matchers()

    private val loadedAccountsLiveData = MutableLiveData<List<AccountEntity>>()

    private val mockedViewModel: AccountsViewModel = mockk(relaxed = true) {
        every { loadedAccounts } returns loadedAccountsLiveData
    }

    @Test
    fun when_UserHasTwoAccounts_then_TwoAccountsAreShownInTheList() {
        every { mockedViewModel.loadAccounts() } answers {
            loadedAccountsLiveData.postValue(listOf(ACCOUNT_1, ACCOUNT_2))
        }

        launchFragment()

        matchers.a(R.id.recyclerview_accounts, 0, "Account 1")
        matchers.a(R.id.recyclerview_accounts, 0, AccountType.SAVING.asStringResource)
        matchers.a(R.id.recyclerview_accounts, 1, "Account 2")
        matchers.a(R.id.recyclerview_accounts, 1, AccountType.CURRENT.asStringResource)
    }


    private fun launchFragment(): FragmentScenario<AccountsFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return AccountsFragment().apply {
                    viewModelFactory = ViewModelFactory(mockedViewModel)

                }
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light)
    }

    companion object {
        val ACCOUNT_1 = AccountEntity(
            1,
            "Account 1",
            "5729263537383030263",
            AccountType.SAVING.id,
            5000,
            0,
            AccountColor.RED.id
        )

        val ACCOUNT_2 = AccountEntity(
            2,
            "Account 2",
            "739393736367393344",
            AccountType.CURRENT.id,
            9000,
            0,
            AccountColor.RED.id
        )
    }
}