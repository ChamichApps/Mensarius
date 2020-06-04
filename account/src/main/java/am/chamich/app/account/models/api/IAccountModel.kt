package am.chamich.app.account.models.api

import am.chamich.app.account.extensions.getAsColor
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.TypeModel
import android.content.Context

internal interface IAccountModel {
    val id: Long
    val name: String
    val number: String
    val value: Long
    val type: Int
    val currency: Int
    val color: Int

    fun getTypeModel() = TypeModel.from(type)

    fun getCurrencyModel() = CurrencyModel.from(currency)

    fun getColorModel() = ColorModel.from(color)

    fun getColor(context: Context) = context.getAsColor(ColorModel.from(color).colorResource)
}