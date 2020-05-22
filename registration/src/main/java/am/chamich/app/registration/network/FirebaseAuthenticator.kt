package am.chamich.app.registration.network

import am.chamich.app.registration.model.User
import am.chamich.app.registration.model.api.IUser
import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticator(
    private val context: Context,
    private val authenticator: FirebaseAuth
) : IAuthenticator {

    override suspend fun signIn(email: String, password: String): IUser {
        Log.d(TAG, "------------------------| Sign In |------------------------")
        authenticator.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d(TAG, "Sign In Successful for [$email]")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Sign In Failed for [$email]", exception)
            }

        return User(1)
    }

    override suspend fun signUp(email: String, password: String): IUser {
        Log.d(TAG, "------------------------| Sign Up |------------------------")
        authenticator.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d(TAG, "Sign Up Successful for [$email]")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Sign Up Failed for [$email]", exception)
            }

        return User(2)
    }

    override suspend fun restorePassword(email: String) {
        Log.d(TAG, "--------------------| Restore Password |-------------------")
        authenticator.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Log.d(TAG, "Password Reset Successful for [$email]")
            }
            .addOnFailureListener {
                Log.e(TAG, "Password Reset Failed for [$email]")
            }
    }

    companion object {
        private const val TAG = "FIREBASE AUTHENTICATOR"
    }
}