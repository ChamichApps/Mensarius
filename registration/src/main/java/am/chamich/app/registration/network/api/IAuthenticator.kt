package am.chamich.app.registration.network.api

import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.model.api.IUser

internal interface IAuthenticator {

    /**
     * Sign In with provided email and password
     */
    @Throws(Failure.SignInException::class)
    suspend fun signIn(email: String, password: String): IUser

    /**
     * Sign Up with provided email and password
     */
    @Throws(Failure.SignUpException::class)
    suspend fun signUp(email: String, password: String): IUser

    /**
     * Restores password for the provided email
     */
    @Throws(Failure.PasswordRecoveryException::class)
    suspend fun restorePassword(email: String): String

    /**
     * Checks if used is Signed In
     * @return true is signed in; otherwise false
     */
    fun isSignedIn(): Boolean

    /**
     * Initiates Sign Out process
     */
    fun signOut()
}
