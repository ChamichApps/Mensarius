package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.RegistrationFragmentSignInBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.*
import am.chamich.app.registration.features.EXTRA_USER_EMAIL
import am.chamich.app.registration.features.EXTRA_USER_ID
import am.chamich.app.registration.features.EXTRA_USER_NAME
import am.chamich.app.registration.features.RESULT_SIGN_IN_SUCCESS
import am.chamich.app.registration.model.api.IUser
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

class SignInFragment : CoreFragment<RegistrationFragmentSignInBinding>() {

    private lateinit var signInViewModel: SignInViewModel

    override var layoutId: Int = R.layout.registration_fragment_sign_in

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = ClickListeners()
    }

    private fun handleSignInSuccess(user: IUser?) {
        hideProgress()
        navigator.finishActivityWithResult(RESULT_SIGN_IN_SUCCESS, Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
            putExtra(EXTRA_USER_EMAIL, user?.email)
            putExtra(EXTRA_USER_NAME, user?.name)
        })
    }

    private fun handleSignInFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext()
            .createToast(failure?.message)
            .apply { show() }
    }

    inner class ClickListeners {
        fun onSignUpClicked() {
            navigator.navigate(this@SignInFragment, R.id.destination_fragment_sign_up)
        }

        fun onRestorePasswordClicked() {
            navigator.navigate(this@SignInFragment, R.id.destination_fragment_restore_password)
        }

        fun onSignInClicked() {
            val isValidEmail = isEmailInputCorrect(binding.layoutInput.textInputLayoutEmail)
            val isValidPassword =
                isPasswordInputCorrect(binding.layoutInput.textInputLayoutPassword)

            if (isValidEmail && isValidPassword) {
                showProgress()
                signInViewModel.signIn(
                    binding.layoutInput.editTextEmail.textAsString,
                    binding.layoutInput.editTextPassword.textAsString
                )
            }
        }
    }
}
