package am.chamich.app.account.features.edit

import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.features.modify.edit.EditAccountFragment
import am.chamich.app.account.features.modify.edit.EditAccountViewModel
import am.chamich.app.account.helpers.*
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel
import am.chamich.app.account.navigation.api.INavigator
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test

internal class EditAccountFragmentTest {

    private val matchers = Matchers()
    private val actions = Actions()

    private lateinit var fragment: EditAccountFragment

    private val loadedAccountLiveData = MutableLiveData<AccountEntity>()

    private val mockedNavigator: INavigator = mockk(relaxed = true)
    private val mockedViewModel: EditAccountViewModel = mockk(relaxed = true) {
        every { loadedAccount } returns loadedAccountLiveData
    }

    @Before
    fun setup() {
        every { mockedViewModel.loadAccount(any()) } answers {
            loadedAccountLiveData.postValue(createAccount())
        }
        launchFragment()
    }

    @Test
    fun when_UserClicksOnTypeSelector_then_CorrectTypeIsSelected() {
        TypeModel.values().forEach { type ->
            actions.performClick(R.id.edittext_account_type)
            matchers.isTextInPopupDisplayed(type.stringResource)

            actions.performClickOnPopupItem(type.stringResource)
            matchers.viewIsDisplayedAndContainsText(type.stringResource)
        }
    }

    @Test
    fun when_UserClicksOnColorSelector_then_CorrectColorIsSelected() {
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
        matchers.viewIsDisplayedAndContainsText(DEFAULT_ACCOUNT_NAME)
        matchers.viewIsDisplayedAndContainsText(DEFAULT_ACCOUNT_NUMBER)
        matchers.viewIsDisplayedAndContainsText(TypeModel.from(DEFAULT_ACCOUNT_TYPE).stringResource)
        matchers.viewIsDisplayedContainsTextAndDisabled(CurrencyModel.from(DEFAULT_CURRENCY).stringResource)
        matchers.viewIsDisplayedAndContainsText(ColorModel.from(DEFAULT_COLOR).colorResource)
    }

    @Test
    fun when_UserPressDeletes_then_AccountDeletedAndFragmentNavigatesBack() {
        fragment.onOptionsItemSelected(mockk(relaxed = true) {
            every { itemId } returns R.id.action_delete
        })

        verify { mockedViewModel.deleteAccount(DEFAULT_ACCOUNT_ID) }
        verify { mockedNavigator.navigateBack(fragment) }
    }

    @Test
    fun when_UserPressSaveWithoutModifyingAccountData_then_AccountSavedAndFragmentNavigatesBack() {
        actions.enterText(R.id.edittext_account_name, DEFAULT_ACCOUNT_NAME)

        fragment.onOptionsItemSelected(mockk(relaxed = true) {
            every { itemId } returns R.id.action_save
        })

        val slot = slot<AccountEntity>()
        verify { mockedViewModel.saveAccount(capture(slot)) }

        slot.captured.run {
            assertThat(name, `is`(DEFAULT_ACCOUNT_NAME))
            assertThat(number, `is`(DEFAULT_ACCOUNT_NUMBER))
            assertThat(type, `is`(DEFAULT_ACCOUNT_TYPE))
            assertThat(color, `is`(DEFAULT_COLOR))
            assertThat(id, `is`(DEFAULT_ACCOUNT_ID))
            assertThat(currency, `is`(DEFAULT_CURRENCY))
        }

        verify { mockedNavigator.navigateBack(fragment) }
    }

    @Test
    fun when_UserPressSaveAfterModifyingAccountData_then_AccountSavedAndFragmentNavigatesBack() {
        actions.enterText(R.id.edittext_account_name, ACCOUNT_EDITED_NAME)
        actions.enterText(R.id.edittext_bank_account_number, ACCOUNT_EDITED_NUMBER)
        actions.performClick(R.id.edittext_account_color)
        actions.performClickOnPopupItem(ACCOUNT_EDITED_COLOR.colorResource)
        actions.performClick(R.id.edittext_account_type)
        actions.performClickOnPopupItem(ACCOUNT_EDITED_TYPE.stringResource)

        fragment.onOptionsItemSelected(mockk(relaxed = true) {
            every { itemId } returns R.id.action_save
        })

        val slot = slot<AccountEntity>()
        verify { mockedViewModel.saveAccount(capture(slot)) }

        slot.captured.run {
            assertThat(name, `is`(ACCOUNT_EDITED_NAME))
            assertThat(number, `is`(ACCOUNT_EDITED_NUMBER))
            assertThat(type, `is`(ACCOUNT_EDITED_TYPE.id))
            assertThat(color, `is`(ACCOUNT_EDITED_COLOR.id))
            assertThat(id, `is`(DEFAULT_ACCOUNT_ID))
            assertThat(currency, `is`(DEFAULT_CURRENCY))
        }

        verify { mockedNavigator.navigateBack(fragment) }
    }

    private fun launchFragment(): FragmentScenario<EditAccountFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                fragment = EditAccountFragment()
                    .apply {
                        viewModelFactory = ViewModelFactory(mockedViewModel)
                        navigator = mockedNavigator
                    }
                return fragment
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light, fragmentArgs = Bundle().apply {
            putLong("accountId", DEFAULT_ACCOUNT_ID)
        })
    }

    companion object {
        const val ACCOUNT_EDITED_NAME = "Edited Name"
        const val ACCOUNT_EDITED_NUMBER = "EDITED63786263273268762382"
        val ACCOUNT_EDITED_COLOR = ColorModel.INDIGO
        val ACCOUNT_EDITED_TYPE = TypeModel.INSURANCE

    }
}