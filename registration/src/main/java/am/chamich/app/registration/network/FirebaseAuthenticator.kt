package am.chamich.app.registration.network

import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.model.UnknownUser
import am.chamich.app.registration.model.User
import am.chamich.app.registration.model.api.IUser
import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticator(
    private val context: Context,
    private val authenticator: FirebaseAuth
) : IAuthenticator {

    init {
        Log.d(TAG, "|-------------| Firebase Authenticator Created |------------|")
        Log.d(TAG, "|---- Authenticator Object ID: ${System.identityHashCode(this)}")

    }

    @Throws(Failure.SignInException::class)
    override suspend fun signIn(email: String, password: String): IUser {
        Log.d(TAG, "|------------------------| Sign In |------------------------|")
        try {
            val data = authenticator.signInWithEmailAndPassword(email, password).await()
            val user = data.user
            return if (user != null) {
                User(user.uid, user.email, user.displayName)
            } else {
                UnknownUser()
            }
        } catch (exception: Exception) {
            throw Failure.SignInException(exception.localizedMessage)
        }
    }

    @Throws(Failure.SignUpException::class)
    override suspend fun signUp(email: String, password: String): IUser {
        Log.d(TAG, "|------------------------| Sign Up |------------------------|")
        try {
            val data = authenticator.createUserWithEmailAndPassword(email, password).await()
            val user = data.user
            return if (user != null) {
                return User(user.uid, user.email, user.displayName)
            } else {
                UnknownUser()
            }
        } catch (exception: Exception) {
            throw Failure.SignUpException(exception.message)
        }
    }

    @Throws(Failure.PasswordRecoveryException::class)
    override suspend fun restorePassword(email: String): String {
        Log.d(TAG, "|--------------------| Restore Password |-------------------|")
        try {
            authenticator.sendPasswordResetEmail(email).await()
            return email
        } catch (exception: Exception) {
            throw Failure.PasswordRecoveryException(exception.localizedMessage)
        }
    }

    companion object {
        private const val TAG = "FIREBASE AUTHENTICATOR"
    }
}