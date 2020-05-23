package am.chamich.app.registration.features.signup

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.FragmentSignUpBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.*
import am.chamich.app.registration.features.EXTRA_USER_EMAIL
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.features.EXTRA_USER_NAME
import am.chamich.app.registration.features.RESULT_SIGN_UP_SUCCESS
import am.chamich.app.registration.model.api.IUser
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class SignUpFragment : CoreFragment() {

    private lateinit var binding: FragmentSignUpBinding
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
            inflater, R.layout.fragment_sign_up, container, false
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

    private fun handleSignUpSuccess(user: IUser?) {
        hideProgress()
        navigator.finishActivityWithResult(RESULT_SIGN_UP_SUCCESS, Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
            putExtra(EXTRA_USER_EMAIL, user?.email)
            putExtra(EXTRA_USER_NAME, user?.name)
        })
    }

    private fun handleSignUpFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext().createToast(R.string.error_sign_up_failed).apply { show() }
    }
}
