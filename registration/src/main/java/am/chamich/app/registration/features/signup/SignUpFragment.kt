package am.chamich.app.registration.features.signup

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignUpFragmentBinding
import am.chamich.app.registration.extensions.observe
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.extensions.viewModel
import am.chamich.app.registration.model.User
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class SignUpFragment : CoreFragment() {

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel = viewModel(viewModelFactory) {
            observe(signedUpUser, ::handleSignedUpUser)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.sign_up_fragment, container, false
        )
        binding.fragment = this
        return binding.root
    }

    fun onSignUpClicked() {
        val isValidEmail = isEmailInputCorrect(binding.textInputLayoutEmail)
        val isValidPassword = isPasswordInputCorrect(binding.textInputLayoutPassword)

        if (isValidEmail && isValidPassword) {
            signUpViewModel.signUp(
                binding.editTextEmail.textAsString,
                binding.editTextPassword.textAsString
            )
        }
    }

    fun onSignInClicked() {
        navigator.navigate(this, R.id.destination_fragment_sign_in)
    }

    private fun handleSignedUpUser(user: User?) {

    }
}
