package am.chamich.app.account.extensions

import am.chamich.app.account.R
import am.chamich.app.account.models.AccountColor
import am.chamich.app.account.models.AccountColor.RED

internal val AccountColor.asColorResource
    get() = when (this) {
        RED -> R.color.account_color_white
    }