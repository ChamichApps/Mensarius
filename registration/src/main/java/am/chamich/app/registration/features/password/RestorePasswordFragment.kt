package am.chamich.app.registration.features.password

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.RegistrationFragmentRestorePasswordBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.createToast
import am.chamich.app.registration.extensions.observe
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.extensions.viewModel
import android.content.Context
import android.os.Bundle
import android.view.View

class RestorePasswordFragment : CoreFragment<RegistrationFragmentRestorePasswordBinding>() {

    private lateinit var restorePasswordViewModel: RestorePasswordViewModel

    override var layoutId: Int = R.layout.registration_fragment_restore_password

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restorePasswordViewModel = viewModel(viewModelFactory) {
            observe(passwordSendEmail, ::handlePasswordRestoreSuccess)
            observe(passwordSendFailure, ::handlePasswordRestoreFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = ClickListeners()
    }

    private fun handlePasswordRestoreSuccess(email: String?) {
        toast = requireContext()
            .createToast(R.string.registration_text_password_restore_success)
            .apply { show() }
    }

    private fun handlePasswordRestoreFailure(failure: Failure?) {
        toast = requireContext()
            .createToast(failure?.message)
            .apply { show() }
    }

    inner class ClickListeners {
        fun onPasswordRestoreClicked() {
            if (isEmailInputCorrect(binding.textInputLayoutEmail)) {
                restorePasswordViewModel.restorePassword(binding.editTextEmail.textAsString)
            }
        }
    }
}
