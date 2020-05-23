package am.chamich.app.registration.exceptions


sealed class Failure(
    override val message: String?
) : Exception() {
    class SignInException(override val message: String?) : Failure(message)
    class SignUpException(override val message: String?) : Failure(message)
    class PasswordRecoveryException(override val message: String?) : Failure(message)
}