package am.chamich.app.registration.features.password

import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RestorePasswordViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : ViewModel() {

    fun restorePassword(email: String) {

    }

}
