package am.chamich.app.registration.features.password

import am.chamich.app.registration.core.CoreViewModel
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestorePasswordViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : CoreViewModel() {

    private val success: MutableLiveData<String> = MutableLiveData()
    val passwordSendEmail: LiveData<String> = success
    val passwordSendFailure: LiveData<Failure> = failure

    fun restorePassword(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    success.postValue(authenticator.restorePassword(email))
                } catch (e: Failure.PasswordRecoveryException) {
                    failure.postValue(e)
                }
            }
        }
    }

}
