package am.chamich.app.registration

import am.chamich.app.registration.features.signin.SignInViewModel
import am.chamich.app.registration.network.IAuthenticator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val authenticator: IAuthenticator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(authenticator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}