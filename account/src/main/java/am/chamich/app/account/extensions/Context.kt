package am.chamich.app.account.extensions

import am.chamich.app.account.R
import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getAsColor(@ColorRes colorResource: Int?) =
    ContextCompat.getColor(this, colorResource ?: R.color.account_color_white)