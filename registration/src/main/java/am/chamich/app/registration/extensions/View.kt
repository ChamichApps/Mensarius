package am.chamich.app.registration.extensions

import android.widget.EditText

internal val EditText.textAsString
    get() = this.text.toString()