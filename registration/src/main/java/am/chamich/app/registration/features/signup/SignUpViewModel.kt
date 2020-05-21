package am.chamich.app.registration.features.signup

import am.chamich.app.registration.model.User
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : ViewModel() {

    private val user: MutableLiveData<User> = MutableLiveData()
    val signedUpUser: LiveData<User>
        get() = user

    fun signUp(email: String, password: String) {
        // authenticator.signUp(email, password)
    }
}
