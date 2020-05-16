package am.chamich.app.registration.extensions

import com.google.android.material.textfield.TextInputEditText

val TextInputEditText.textAsString
    get() = this.text.toString()