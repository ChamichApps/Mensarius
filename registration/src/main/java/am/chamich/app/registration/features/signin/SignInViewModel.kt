package am.chamich.app.registration.features.signin

import am.chamich.app.registration.core.CoreViewModel
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.model.api.IUser
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : CoreViewModel() {

    private val success: MutableLiveData<IUser> = MutableLiveData()
    val signedInUser: LiveData<IUser> = success
    val signInFailure: LiveData<Failure> = failure

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    success.postValue(authenticator.signIn(email, password))
                } catch (e: Failure.SignInException) {
                    failure.postValue(e)
                }
            }
        }
    }
}
