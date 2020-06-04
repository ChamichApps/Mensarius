package am.chamich.app.account.features.accounts

import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.helpers.Matchers
import am.chamich.app.account.helpers.ViewModelFactory
import am.chamich.app.account.helpers.createAccount
import am.chamich.app.account.models.TypeModel
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
            loadedAccountsLiveData.postValue(
                listOf(
                    createAccount(name = ACCOUNT_1_NAME, type = ACCOUNT_1_TYPE.id),
                    createAccount(name = ACCOUNT_2_NAME, type = ACCOUNT_2_TYPE.id)
                )
            )
        }

        launchFragment()

        matchers.a(R.id.recyclerview_accounts, 0, ACCOUNT_1_NAME)
        matchers.a(R.id.recyclerview_accounts, 0, ACCOUNT_1_TYPE.stringResource)
        matchers.a(R.id.recyclerview_accounts, 1, ACCOUNT_2_NAME)
        matchers.a(R.id.recyclerview_accounts, 1, ACCOUNT_2_TYPE.stringResource)
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
        const val ACCOUNT_1_NAME = "Account 1"
        const val ACCOUNT_2_NAME = "Account 2"
        val ACCOUNT_1_TYPE = TypeModel.CURRENT
        val ACCOUNT_2_TYPE = TypeModel.SAVING
    }
}