package am.chamich.app.account.extensions

import am.chamich.app.account.R
import am.chamich.app.account.models.AccountType
import am.chamich.app.account.models.AccountType.*

internal val AccountType.asStringResource
    get() = when (this) {
        BONUS -> R.string.account_type_bonus
        CASH -> R.string.account_type_cash
        CREDIT -> R.string.account_type_credit_card
        GENERAL -> R.string.account_type_general
        INSURANCE -> R.string.account_type_insurance
        INVESTMENT -> R.string.account_type_investment
        LOAN -> R.string.account_type_loan
        MORTGAGE -> R.string.account_type_mortgage
        OVERDRAFT -> R.string.account_type_overdraft
        SAVING -> R.string.account_type_saving
        CURRENT -> R.string.account_type_current
    }