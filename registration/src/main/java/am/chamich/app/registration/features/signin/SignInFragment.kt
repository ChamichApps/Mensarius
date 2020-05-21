package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignInFragmentBinding
import am.chamich.app.registration.extensions.observe
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.extensions.viewModel
import am.chamich.app.registration.model.User
import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class SignInFragment : CoreFragment() {

    private lateinit var binding: SignInFragmentBinding
    private lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInViewModel = viewModel(viewModelFactory) {
            observe(signedInUser, ::handleSignedInUser)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.sign_in_fragment, container, false
        )
        binding.fragment = this
        return binding.root
    }

    fun onSignInClicked() {
        val isValidEmail = isEmailInputCorrect(binding.textInputLayoutEmail)
        val isValidPassword = isPasswordInputCorrect(binding.textInputLayoutPassword)

        if (isValidEmail && isValidPassword) {
            signInViewModel.signIn(
                binding.editTextEmail.textAsString,
                binding.editTextPassword.textAsString
            )
        }
    }

    fun onSignUpClicked() {
        navigator.navigate(this, R.id.destination_fragment_sign_up)
    }

    fun onRestorePasswordClicked() {
        navigator.navigate(this, R.id.destination_fragment_restore_password)
    }

    private fun handleSignedInUser(user: User?) {
        navigator.finishActivityWithResult(RESULT_OK)
    }
}
