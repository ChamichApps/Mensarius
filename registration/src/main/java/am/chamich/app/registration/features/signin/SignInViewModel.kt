package am.chamich.app.registration.features.signin

import am.chamich.app.registration.model.User
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authenticator: IAuthenticator
) : ViewModel() {

    private val user: MutableLiveData<User> = MutableLiveData()
    val signedInUser: LiveData<User>
        get() = user

    fun signIn(email: String, password: String) {
        var result: User? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                result = authenticator.signIn(email, password)
            }
            user.value = result
        }
    }
}
