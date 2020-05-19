package am.chamich.app.registration.network

import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticator(
    private val context: Context,
    private val authenticator: FirebaseAuth
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