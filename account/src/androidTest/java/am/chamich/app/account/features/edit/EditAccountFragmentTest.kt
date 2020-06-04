package am.chamich.app.account.features.edit

import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.features.modify.edit.EditAccountFragment
import am.chamich.app.account.features.modify.edit.EditAccountViewModel
import am.chamich.app.account.helpers.*
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class EditAccountFragmentTest {

    private val matchers = Matchers()
    private val actions = Actions()

    private val loadedAccountLiveData = MutableLiveData<AccountEntity>()

    private val mockedViewModel: EditAccountViewModel = mockk(relaxed = true) {
        every { loadedAccount } returns loadedAccountLiveData
    }

    @Test
    fun when_UserClicksOnTypeSelector_then_CorrectTypeIsSelected() {
        every { mockedViewModel.loadAccount(any()) } answers {
            loadedAccountLiveData.postValue(createAccount())
        }

        launchFragment()

        TypeModel.values().forEach { type ->
            actions.performClick(R.id.edittext_account_type)
            matchers.isTextInPopupDisplayed(type.stringResource)

            actions.performClickOnPopupItem(type.stringResource)
            matchers.viewIsDisplayedAndContainsText(type.stringResource)
        }
    }

    @Test
    fun when_UserClicksOnColorSelector_then_CorrectColorIsSelected() {
        every { mockedViewModel.loadAccount(any()) } answers {
            loadedAccountLiveData.postValue(createAccount())
        }

        launchFragment()

        ColorModel.values().forEach { color ->
            actions.performClick(R.id.edittext_account_color)
            matchers.isTextInPopupDisplayed(color.colorResource)

            actions.performClickOnPopupItem(color.colorResource)
            matchers.viewIsDisplayedAndContainsText(color.colorResource)
            Thread.sleep(50)
        }
    }

    @Test
    fun when_UserSelectsAccount_then_AccountDataIsShown() {
        every { mockedViewModel.loadAccount(any()) } answers {
            loadedAccountLiveData.postValue(createAccount())
        }

        launchFragment()

        matchers.viewIsDisplayedAndContainsText(DEFAULT_ACCOUNT_NAME)
        matchers.viewIsDisplayedAndContainsText(DEFAULT_ACCOUNT_NUMBER)
        matchers.viewIsDisplayedAndContainsText(TypeModel.from(DEFAULT_ACCOUNT_TYPE).stringResource)
        matchers.viewIsDisplayedContainsTextAndDisabled(CurrencyModel.from(DEFAULT_CURRENCY).stringResource)
        matchers.viewIsDisplayedAndContainsText(ColorModel.from(DEFAULT_COLOR).colorResource)
    }


    private fun launchFragment(): FragmentScenario<EditAccountFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return EditAccountFragment()
                    .apply {
                        viewModelFactory = ViewModelFactory(mockedViewModel)
                    }
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light, fragmentArgs = Bundle().apply {
            putLong("accountId", 1)
        })
    }
}