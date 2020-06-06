package am.chamich.app.account.features.modify.add

import am.chamich.app.account.R
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.features.modify.edit.EditAccountFragment
import am.chamich.app.account.helpers.Actions
import am.chamich.app.account.helpers.DEFAULT_ACCOUNT_ID
import am.chamich.app.account.helpers.Matchers
import am.chamich.app.account.helpers.ViewModelFactory
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel
import am.chamich.app.account.navigation.api.INavigator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

internal class AddAccountFragmentTest {

    private lateinit var fragment: AddAccountFragment

    private val matchers = Matchers()
    private val actions = Actions()

    private val loadedAccountLiveData = MutableLiveData<Long>()

    private val mockedViewModel: AddAccountViewModel = mockk(relaxed = true) {
        every { loadedAccount } returns loadedAccountLiveData
    }
    private val mockedNavigator: INavigator = mockk(relaxed = true)

    @Before
    fun setup() {
        launchFragment()
    }

    @Test
    fun when_UserSwitchesAccountTypes_then_UserInterfaceReactsOnChanges() {
        TypeModel.values().forEach { type ->
            actions.performClick(R.id.edittext_account_type)
            matchers.isTextInPopupDisplayed(type.stringResource)

            actions.performClickOnPopupItem(type.stringResource)
            matchers.viewIsDisplayedAndContainsText(type.stringResource)
        }
    }

    @Test
    fun when_UserSwitchesCurrency_then_UserInterfaceReactsOnChanges() {
        CurrencyModel.values().forEach { currency ->
            actions.performClick(R.id.edittext_account_currency)
            matchers.isTextInPopupDisplayed(currency.stringResource)

            actions.performClickOnPopupItem(currency.stringResource)
            matchers.viewIsDisplayedAndContainsText(currency.stringResource)
        }
    }

    @Test
    fun when_UserSwitchesColors_then_UserInterfaceReactsOnChanges() {
        ColorModel.values().forEach { color ->
            actions.performClick(R.id.edittext_account_color)
            matchers.isTextInPopupDisplayed(color.colorResource)

            actions.performClickOnPopupItem(color.colorResource)
            matchers.viewIsDisplayedAndContainsText(color.colorResource)
            Thread.sleep(50)
        }
    }

    @Test
    fun when_UserAddsNewAccount_then_ViewModelReactsOnTheEvent() {
        every { mockedViewModel.saveAccount(any()) } answers {
            loadedAccountLiveData.postValue(DEFAULT_ACCOUNT_ID)
        }

        actions.enterText(R.id.edittext_account_name, NEW_ACCOUNT_NAME)
        actions.enterText(R.id.edittext_bank_account_number, NEW_ACCOUNT_NUMBER)
        actions.enterText(R.id.edittext_initial_value, NEW_ACCOUNT_INITIAL_VALUE)
        actions.performClick(R.id.edittext_account_type)
        actions.performClickOnPopupItem(NEW_ACCOUNT_TYPE.stringResource)
        actions.performClick(R.id.edittext_account_currency)
        actions.performClickOnPopupItem(NEW_ACCOUNT_CURRENCY.stringResource)
        actions.performClick(R.id.edittext_account_color)
        actions.performClickOnPopupItem(NEW_ACCOUNT_COLOR.colorResource)

        fragment.onOptionsItemSelected(mockk(relaxed = true) {
            every { itemId } returns R.id.action_save
        })

        val slot = slot<AccountEntity>()
        verify { mockedViewModel.saveAccount(capture(slot)) }

        slot.captured.run {
            assertThat(name, `is`(NEW_ACCOUNT_NAME))
            assertThat(number, `is`(NEW_ACCOUNT_NUMBER))
            assertThat(value, `is`(NEW_ACCOUNT_INITIAL_VALUE.toLong()))
            assertThat(type, `is`(NEW_ACCOUNT_TYPE.id))
            assertThat(color, `is`(NEW_ACCOUNT_COLOR.id))
            assertThat(currency, `is`(NEW_ACCOUNT_CURRENCY.id))
        }

        verify { mockedNavigator.navigateBack(fragment) }
    }

    private fun launchFragment(): FragmentScenario<EditAccountFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                fragment = AddAccountFragment()
                    .apply {
                        viewModelFactory = ViewModelFactory(mockedViewModel)
                        navigator = mockedNavigator
                    }
                return fragment
            }
        }, themeResId = R.style.Theme_MaterialComponents_Light)
    }

    companion object {
        const val NEW_ACCOUNT_NAME = "New Bank Account"
        const val NEW_ACCOUNT_NUMBER = "NEW6306474890948406479784484"
        const val NEW_ACCOUNT_INITIAL_VALUE = "90000"
        val NEW_ACCOUNT_TYPE = TypeModel.BONUS
        val NEW_ACCOUNT_CURRENCY = CurrencyModel.EURO
        val NEW_ACCOUNT_COLOR = ColorModel.RED
    }
}