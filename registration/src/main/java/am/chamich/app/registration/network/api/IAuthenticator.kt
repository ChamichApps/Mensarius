package am.chamich.app.registration.network.api

import am.chamich.app.registration.model.api.IUser

interface IAuthenticator {

    /**
     * Sign In with provided email and password
     */
    suspend fun signIn(email: String, password: String): IUser

    /**
     * Sign Up with provided email and password
     */
    suspend fun signUp(email: String, password: String): IUser

    /**
     * Restores password for the provided email
     */
    suspend fun restorePassword(email: String)
}
