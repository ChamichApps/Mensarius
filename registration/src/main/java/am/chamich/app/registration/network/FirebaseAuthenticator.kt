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

internal class FirebaseAuthenticator(
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
                Log.d(
                    TAG, "|---- Signed In with ID: " +
                            "${user.uid}, Email: ${user.email}, Display Name: ${user.displayName}"
                )
                User(user.uid, user.email, user.displayName)
            } else {
                UnknownUser()
            }
        } catch (exception: Exception) {
            Log.e(TAG, "Sign In Failed", exception)
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
                Log.d(
                    TAG, "|---- Signed Up with ID: " +
                            "${user.uid}, Email: ${user.email}, Display Name: ${user.displayName}"
                )
                return User(user.uid, user.email, user.displayName)
            } else {
                UnknownUser()
            }
        } catch (exception: Exception) {
            Log.e(TAG, "Sign Up Failed", exception)
            throw Failure.SignUpException(exception.localizedMessage)
        }
    }

    @Throws(Failure.PasswordRecoveryException::class)
    override suspend fun restorePassword(email: String): String {
        Log.d(TAG, "|--------------------| Restore Password |-------------------|")
        try {
            authenticator.sendPasswordResetEmail(email).await()
            Log.d(TAG, "Password Restore instructions send to: $email")
            return email
        } catch (exception: Exception) {
            Log.e(TAG, "Password Restore Failed", exception)
            throw Failure.PasswordRecoveryException(exception.localizedMessage)
        }
    }

    override fun isSignedIn(): Boolean {
        Log.d(TAG, "|----------------------| Is Signed In |---------------------|")
        val isSignedIn = authenticator.currentUser != null
        Log.d(TAG, "|---- User Signed In: $isSignedIn")
        return isSignedIn
    }

    override fun signOut() {
        Log.d(TAG, "|------------------------| Sign Out |-----------------------|")
        authenticator.signOut()
    }

    companion object {
        private const val TAG = "FIREBASE AUTHENTICATOR"
    }
}