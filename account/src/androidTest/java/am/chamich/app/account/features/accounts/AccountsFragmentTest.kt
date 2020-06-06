package am.chamich.app.account.features.accounts

import am.chamich.app.account.AccountTestHelper
import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.helpers.ViewModelFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class AccountsFragmentTest : AccountTestHelper() {

    private val loadedAccountsLiveData = MutableLiveData<List<AccountEntity>>()

    private val mockedViewModel: AccountsViewModel = mockk(relaxed = true) {
        every { loadedAccounts } returns loadedAccountsLiveData
    }

    @Test
    fun when_UserHasTwoAccounts_then_TwoAccountsAreShownInTheList() {
        every { mockedViewModel.loadAccounts() } answers {
            loadedAccountsLiveData.postValue(listOf(createAccount(0), createAccount(1)))
        }

        launchFragment()

        verifyAccountsInRecyclerView(listOf(0, 1))
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
}