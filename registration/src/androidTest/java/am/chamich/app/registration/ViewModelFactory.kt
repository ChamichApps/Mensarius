package am.chamich.app.registration

import am.chamich.app.registration.features.signin.SignInViewModel
import am.chamich.app.registration.features.signup.SignUpViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val mockedViewModel: ViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java) ||
            modelClass.isAssignableFrom(SignUpViewModel::class.java)
        ) {
            return mockedViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}