package am.chamich.app.registration.features.password

import am.chamich.app.registration.core.CoreViewModel
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class RestorePasswordViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : CoreViewModel() {

    private val email: MutableLiveData<String> = MutableLiveData()
    val passwordSendEmail: LiveData<String>
        get() = email
    val passwordSendFailure: LiveData<Failure>
        get() = failure

    fun restorePassword(email: String) {

    }

}
