package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.databinding.SignInFragmentBinding
import am.chamich.app.registration.extensions.isValidEmail
import am.chamich.app.registration.extensions.isValidPassword
import am.chamich.app.registration.features.RegistrationActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: SignInFragmentBinding
    private lateinit var viewModel: SignInViewModel

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    fun onSignInClicked(view: View) {
        if (isEmailInputCorrect() && isPasswordInputCorrect()) {
            viewModel.signIn("", "")
        }
    }

    fun onSignUpClicked(view: View) {
        findNavController().navigate(R.id.destination_fragment_sign_up)
    }

    fun onRestorePasswordClicked(view: View) {
        findNavController().navigate(R.id.destination_fragment_restore_password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
    }

    private fun isEmailInputCorrect() = binding.editTextEmail.text.toString().isValidEmail.apply {
        val textInputLayout = binding.textInputLayoutEmail
        textInputLayout.error = if (!this) getString(R.string.error_email_invalid) else null
        textInputLayout.isErrorEnabled = !this
    }

    private fun isPasswordInputCorrect() =
        binding.editTextPassword.text.toString().isValidPassword.apply {
            val textInputLayout = binding.textInputLayoutPassword
            textInputLayout.error = if (!this) getString(R.string.error_password_invalid) else null
            textInputLayout.isErrorEnabled = !this
    }

    private fun inject() {
        (requireActivity() as? RegistrationActivity)
            ?.activityComponent
            ?.inject(this)
    }
}
