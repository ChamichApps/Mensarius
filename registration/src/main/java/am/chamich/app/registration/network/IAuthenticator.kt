package am.chamich.app.registration.network

interface IAuthenticator {

    /**
     * Sign In with provided email and password
     */
    fun signIn(email: String, password: String)

    /**
     * Sign Up with provided email and password
     */
    fun signUp(email: String, password: String)

    /**
     * Restores password for the provided email
     */
    fun restorePassword(email: String)
}