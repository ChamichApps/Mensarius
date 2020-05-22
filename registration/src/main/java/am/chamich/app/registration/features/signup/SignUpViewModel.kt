package am.chamich.app.registration.features.signup

import am.chamich.app.registration.core.CoreViewModel
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.model.api.IUser
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : CoreViewModel() {

    private val user: MutableLiveData<IUser> = MutableLiveData()
    val signedUpUser: LiveData<IUser>
        get() = user
    val signUpFailure: LiveData<Failure>
        get() = failure

    fun signUp(email: String, password: String) {
        // authenticator.signUp(email, password)
    }
}
