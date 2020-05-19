package am.chamich.app.registration.features.signin

import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : ViewModel() {

    fun signIn(email: String, password: String) {
        authenticator.signIn(email, password)
    }

}
