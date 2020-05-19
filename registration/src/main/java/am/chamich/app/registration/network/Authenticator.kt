package am.chamich.app.registration.network

import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import android.util.Log

class Authenticator(
    private val context: Context
) : IAuthenticator {

    init {
        Log.d(TAG, "Authenticator Created")
    }

    override fun signIn(email: String, password: String) {

    }

    override fun signUp(email: String, password: String) {

    }

    override fun restorePassword(email: String) {

    }

    companion object {
        private const val TAG = "Authenticator"
    }
}