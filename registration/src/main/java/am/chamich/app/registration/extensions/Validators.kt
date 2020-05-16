package am.chamich.app.registration.extensions

import java.util.regex.Pattern.compile

private const val EMAIL_PATTERN =
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
private const val PASSWORD_ALLOWED_LENGTH = 6

val String.isValidEmail
    get() = compile(EMAIL_PATTERN).matcher(this).matches()

val String.isValidPassword: Boolean
    get() = compile(PASSWORD_PATTERN)
        .matcher(this).matches() && this.length >= PASSWORD_ALLOWED_LENGTH

