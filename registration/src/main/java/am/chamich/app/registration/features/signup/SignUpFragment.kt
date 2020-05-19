package am.chamich.app.registration.features.signup

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.SignUpFragmentBinding
import am.chamich.app.registration.extensions.textAsString
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class SignUpFragment : CoreFragment() {

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
    }

    fun onSignUpClicked() {
        val isValidEmail = isEmailInputCorrect(binding.textInputLayoutEmail)
        val isValidPassword = isPasswordInputCorrect(binding.textInputLayoutPassword)

        if (isValidEmail && isValidPassword) {
            viewModel.signUp(
                binding.editTextEmail.textAsString,
                binding.editTextPassword.textAsString
            )
        }
    }

    fun onSignInClicked() {
        findNavController().navigate(R.id.destination_fragment_sign_in)
    }

    private fun inject() {
        activityComponent?.inject(this)
    }
}
