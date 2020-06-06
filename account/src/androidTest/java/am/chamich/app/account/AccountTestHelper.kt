package am.chamich.app.account

import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.helpers.Actions
import am.chamich.app.account.helpers.Matchers
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`

internal abstract class AccountTestHelper {

    private val actions = Actions()
    private val matchers = Matchers()

    // Actions

    protected fun fillAccountForm(accountEntity: AccountEntity, isEditing: Boolean = false) {
        actions.enterText(R.id.edittext_account_name, accountEntity.name)
        actions.enterText(R.id.edittext_bank_account_number, accountEntity.number)
        if (!isEditing) {
            actions.enterText(R.id.edittext_initial_value, accountEntity.value.toString())
            actions.performClick(R.id.edittext_account_currency)
            actions.performClickOnPopupItem(CurrencyModel.from(accountEntity.currency).stringResource)
        }
        actions.performClick(R.id.edittext_account_type)
        actions.performClickOnPopupItem(TypeModel.from(accountEntity.type).stringResource)
        actions.performClick(R.id.edittext_account_color)
        actions.performClickOnPopupItem(ColorModel.from(accountEntity.color).colorResource)
    }

    protected fun clickAddAccount() {
        actions.performClick(R.id.floatingactionbutton_add)
    }

    protected fun clickSaveAccount() {
        actions.performClick(R.id.action_save)
    }

    protected fun clickDelete() {
        actions.performClick(R.id.action_delete)
    }

    protected fun clickOnRecyclerViewItem(position: Int) {
        actions.performClickOnItemAtPosition(R.id.recyclerview_accounts, position)
    }

    // Matchers

    protected fun verifyAccountsInRecyclerView(numbers: List<Int>) {
        val id = R.id.recyclerview_accounts
        matchers.run {
            repeat(numbers.size) { position ->
                val name = "$ACCOUNT_NAME ${numbers[position]}"
                val type =
                    TypeModel.from(numbers[position] % TypeModel.values().size).stringResource
                viewIsDisplayedInRecyclerViewAtPosition(id, position, name)
                viewIsDisplayedInRecyclerViewAtPosition(id, position, type)
            }
        }
    }

    protected fun verifyAccountForm(number: Int) {
        val type = TypeModel.from(number % TypeModel.values().size).stringResource
        val color = ColorModel.from(number % ColorModel.values().size).colorResource
        val currency = CurrencyModel.from(number % CurrencyModel.values().size).stringResource

        matchers.viewIsDisplayedAndContainsText("$ACCOUNT_NAME $number")
        matchers.viewIsDisplayedAndContainsText("$ACCOUNT_NUMBER$number")
        matchers.viewIsDisplayedAndContainsText(type)
        matchers.viewIsDisplayedAndContainsText(color)
        matchers.viewIsDisplayedAndContainsText(currency)
    }

    // Assertions

    protected fun assertAccountData(accountEntity: AccountEntity, id: Long) {
        accountEntity.run {
            val accountType = TypeModel.from(id.toInt() % TypeModel.values().size).id
            val accountCurrency = CurrencyModel.from(id.toInt() % CurrencyModel.values().size).id
            val accountColor = ColorModel.from(id.toInt() % ColorModel.values().size).id

            assertThat(name, `is`("$ACCOUNT_NAME $id"))
            assertThat(number, `is`("$ACCOUNT_NUMBER$id"))
            assertThat(value, `is`("$ACCOUNT_INITIAL_VALUE$id".toLong()))
            assertThat(type, `is`(accountType))
            assertThat(currency, `is`(accountCurrency))
            assertThat(color, `is`(accountColor))
        }
    }

    // Helpers

    protected fun createAccount(id: Int) =
        AccountEntity(
            id = id.toLong(),
            name = "$ACCOUNT_NAME $id",
            number = "$ACCOUNT_NUMBER$id",
            value = "$ACCOUNT_INITIAL_VALUE$id".toLong(),
            type = TypeModel.from(id % TypeModel.values().size).id,
            color = ColorModel.from(id % ColorModel.values().size).id,
            currency = CurrencyModel.from(id % CurrencyModel.values().size).id
        )

    companion object {
        const val ACCOUNT_NAME = "Account Name"
        const val ACCOUNT_NUMBER = "DE13243594427494635384"
        const val ACCOUNT_INITIAL_VALUE = "10000"
    }
}