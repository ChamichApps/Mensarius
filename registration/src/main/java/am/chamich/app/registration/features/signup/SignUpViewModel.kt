package am.chamich.app.registration.features.signup

import am.chamich.app.registration.network.IAuthenticator
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : ViewModel() {

    fun signUp(email: String, password: String) {
        authenticator.signUp(email, password)
    }
}
