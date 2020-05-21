package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignInFragmentBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.*
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.model.User
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
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
            failure(signInFailure, ::handleFailure)
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

    private fun handleSignedInUser(user: User?) {
        hideProgress()
        navigator.finishActivityWithResult(RESULT_OK, Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
        })
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext().createToast(R.string.error_sign_in_failed).apply { show() }
    }
}
