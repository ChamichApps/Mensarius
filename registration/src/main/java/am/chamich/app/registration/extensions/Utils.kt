package am.chamich.app.registration.extensions

import android.widget.EditText

val EditText.textAsString
    get() = this.text.toString()