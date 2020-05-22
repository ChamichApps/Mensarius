package am.chamich.app.registration.features.signup

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignUpFragmentBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.*
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.features.RESULT_SIGN_UP_SUCCESS
import am.chamich.app.registration.model.User
import android.content.Context
import android.content.Intent
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
            observe(signedUpUser, ::handleSignUpSuccess)
            failure(signUpFailure, ::handleSignUpFailure)
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
            showProgress()
            signUpViewModel.signUp(
                binding.editTextEmail.textAsString,
                binding.editTextPassword.textAsString
            )
        }
    }

    fun onSignInClicked() {
        navigator.navigate(this, R.id.destination_fragment_sign_in)
    }

    private fun handleSignUpSuccess(user: User?) {
        hideProgress()
        navigator.finishActivityWithResult(RESULT_SIGN_UP_SUCCESS, Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
        })
    }

    private fun handleSignUpFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext().createToast(R.string.error_sign_up_failed).apply { show() }
    }
}
