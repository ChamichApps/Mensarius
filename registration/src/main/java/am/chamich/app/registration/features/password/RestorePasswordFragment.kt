package am.chamich.app.registration.features.password

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.FragmentRestorePasswordBinding
import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.extensions.createToast
import am.chamich.app.registration.extensions.observe
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.extensions.viewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class RestorePasswordFragment : CoreFragment() {

    private lateinit var binding: FragmentRestorePasswordBinding
    private lateinit var restorePasswordViewModel: RestorePasswordViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_restore_password, container, false
        )
        binding.fragment = this
        return binding.root
    }

    fun onPasswordRestoreClicked() {
        if (isEmailInputCorrect(binding.textInputLayoutEmail)) {
            restorePasswordViewModel.restorePassword(binding.editTextEmail.textAsString)
        }
    }

    private fun handlePasswordRestoreSuccess(email: String?) {
        toast =
            requireContext().createToast(R.string.text_password_restore_success).apply { show() }
    }

    private fun handlePasswordRestoreFailure(failure: Failure?) {
        toast = requireContext().createToast(R.string.error_password_restore).apply { show() }
    }
}
