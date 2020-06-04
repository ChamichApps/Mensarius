package am.chamich.app.account.helpers

import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel

const val DEFAULT_ACCOUNT_NAME = "Default Account"
const val DEFAULT_ACCOUNT_ID = 1L
const val DEFAULT_ACCOUNT_NUMBER = "DEFAULT16393826803836352936"
val DEFAULT_ACCOUNT_TYPE = TypeModel.CURRENT.id
const val DEFAULT_ACCOUNT_VALUE = 8000L
val DEFAULT_CURRENCY = CurrencyModel.EURO.id
val DEFAULT_COLOR = ColorModel.BLUE.id

internal fun createAccount(
    id: Long = DEFAULT_ACCOUNT_ID,
    name: String = DEFAULT_ACCOUNT_NAME,
    number: String = DEFAULT_ACCOUNT_NUMBER,
    type: Int = DEFAULT_ACCOUNT_TYPE,
    value: Long = DEFAULT_ACCOUNT_VALUE,
    currency: Int = DEFAULT_CURRENCY,
    color: Int = DEFAULT_COLOR
) = AccountEntity(
    id, name, number, type, value, currency, color
)

