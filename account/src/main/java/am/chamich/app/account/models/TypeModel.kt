package am.chamich.app.account.models

import am.chamich.app.account.R
import androidx.annotation.StringRes

internal enum class TypeModel(val id: Int, @StringRes val stringResource: Int) {
    GENERAL(0, R.string.account_type_general),
    CASH(1, R.string.account_type_cash),
    CURRENT(2, R.string.account_type_current),
    CREDIT(3, R.string.account_type_credit_card),
    SAVING(4, R.string.account_type_saving),
    BONUS(5, R.string.account_type_bonus),
    INSURANCE(6, R.string.account_type_insurance),
    INVESTMENT(7, R.string.account_type_investment),
    LOAN(8, R.string.account_type_loan),
    MORTGAGE(9, R.string.account_type_mortgage),
    OVERDRAFT(10, R.string.account_type_overdraft);

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}