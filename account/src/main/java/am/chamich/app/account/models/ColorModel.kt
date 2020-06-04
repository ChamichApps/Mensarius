package am.chamich.app.account.models

import am.chamich.app.account.R
import am.chamich.app.account.extensions.getAsColor
import android.content.Context
import androidx.annotation.ColorRes

internal enum class ColorModel(val id: Int, @ColorRes val colorResource: Int) {

    RED(0, R.color.account_color_red),
    PINK(1, R.color.account_color_pink),
    PURPLE(2, R.color.account_color_purple),
    DEEP_PURPLE(3, R.color.account_color_deep_purple),
    INDIGO(4, R.color.account_color_indigo),
    BLUE(5, R.color.account_color_blue),
    LIGHT_BLUE(6, R.color.account_color_light_blue);

    fun getColor(context: Context) = context.getAsColor(colorResource)

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}
