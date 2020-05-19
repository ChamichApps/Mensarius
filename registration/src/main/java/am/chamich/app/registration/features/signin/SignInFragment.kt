package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignInFragmentBinding
import am.chamich.app.registration.extensions.textAsString
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class SignInFragment : CoreFragment() {

    private lateinit var binding: SignInFragmentBinding
    private lateinit var viewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
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
            viewModel.signIn(
                binding.editTextEmail.textAsString,
                binding.editTextPassword.textAsString
            )
        }
    }

    fun onSignUpClicked() {
        findNavController().navigate(R.id.destination_fragment_sign_up)
    }

    fun onRestorePasswordClicked() {
        findNavController().navigate(R.id.destination_fragment_restore_password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
    }

    private fun inject() {
        activityComponent?.inject(this)
    }
}
