package am.chamich.app.registration.extensions

val String.isValidEmail
    get() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()