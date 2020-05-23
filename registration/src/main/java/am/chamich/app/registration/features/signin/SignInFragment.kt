package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.FragmentSignInBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.*
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.features.RESULT_SIGN_IN_SUCCESS
import am.chamich.app.registration.model.api.IUser
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class SignInFragment : CoreFragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInViewModel = viewModel(viewModelFactory) {
            observe(signedInUser, ::handleSignInSuccess)
            failure(signInFailure, ::handleSignInFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_in, container, false
        )
        binding.fragment = this
        return binding.root
    }

    fun onSignInClicked() {
        val isValidEmail = isEmailInputCorrect(binding.textInputLayoutEmail)
        val isValidPassword = isPasswordInputCorrect(binding.textInputLayoutPassword)

        if (isValidEmail && isValidPassword) {
            showProgress()
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

    private fun handleSignInSuccess(user: IUser?) {
        hideProgress()
        navigator.finishActivityWithResult(RESULT_SIGN_IN_SUCCESS, Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
        })
    }

    private fun handleSignInFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext().createToast(R.string.error_sign_in_failed).apply { show() }
    }
}
