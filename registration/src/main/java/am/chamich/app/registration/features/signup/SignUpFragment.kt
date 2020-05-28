package am.chamich.app.registration.features.signup

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.RegistrationFragmentSignUpBinding
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
import android.view.View

internal class SignUpFragment : CoreFragment<RegistrationFragmentSignUpBinding>() {

    private lateinit var signUpViewModel: SignUpViewModel

    override var layoutId: Int = R.layout.registration_fragment_sign_up

    override fun onAttach(context: Context) {
        super.onAttach(context)
        registrationComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel = viewModel(viewModelFactory) {
            observe(signedUpUser, ::handleSignUpSuccess)
            failure(signUpFailure, ::handleSignUpFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = ClickListeners()
    }

    private fun handleSignUpSuccess(user: IUser?) {
        hideProgress()
        navigator.finishActivityWithResult(
            requireActivity(),
            RESULT_SIGN_UP_SUCCESS,
            Intent().apply {
            putExtra(EXTRA_USER_ID, user?.id)
            putExtra(EXTRA_USER_EMAIL, user?.email)
            putExtra(EXTRA_USER_NAME, user?.name)
        })
    }

    private fun handleSignUpFailure(failure: Failure?) {
        hideProgress()
        toast = requireContext()
            .createToast(failure?.message)
            .apply { show() }
    }

    inner class ClickListeners {
        fun onSignUpClicked() {
            val isValidEmail = isEmailInputCorrect(binding.layoutInput.textInputLayoutEmail)
            val isValidPassword =
                isPasswordInputCorrect(binding.layoutInput.textInputLayoutPassword)

            if (isValidEmail && isValidPassword) {
                showProgress()
                signUpViewModel.signUp(
                    binding.layoutInput.editTextEmail.textAsString,
                    binding.layoutInput.editTextPassword.textAsString
                )
            }
        }

        fun onSignInClicked() {
            navigator.navigate(this@SignUpFragment, R.id.destination_fragment_sign_in)
        }
    }
}
