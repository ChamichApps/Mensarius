package am.chamich.app.registration.network.api

import am.chamich.app.registration.model.User

interface IAuthenticator {

    /**
     * Sign In with provided email and password
     */
    suspend fun signIn(email: String, password: String): User

    /**
     * Sign Up with provided email and password
     */
    suspend fun signUp(email: String, password: String): User

    /**
     * Restores password for the provided email
     */
    suspend fun restorePassword(email: String)
}
