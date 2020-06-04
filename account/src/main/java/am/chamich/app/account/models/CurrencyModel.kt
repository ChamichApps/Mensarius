package am.chamich.app.account.models

import am.chamich.app.account.R
import androidx.annotation.StringRes

internal enum class CurrencyModel(val id: Int, @StringRes val stringResource: Int) {
    EURO(0, R.string.account_currency_euro),
    USD(1, R.string.account_currency_usd);

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}