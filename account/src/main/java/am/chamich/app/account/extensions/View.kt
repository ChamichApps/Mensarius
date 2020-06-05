package am.chamich.app.account.extensions

import android.widget.EditText

internal val EditText.textAsString
    get() = this.text.toString()