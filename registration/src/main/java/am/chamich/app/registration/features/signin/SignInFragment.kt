package am.chamich.app.registration.features.signin

import am.chamich.app.registration.R
import am.chamich.app.registration.databinding.SignInFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class SignInFragment : Fragment() {

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

    fun onSignUpClicked(view: View) {
        findNavController().navigate(R.id.destination_fragment_sign_up)
    }

    fun onRestorePasswordClicked(view: View) {
        findNavController().navigate(R.id.destination_fragment_restore_password)
    }

    fun onSignInClicked(view: View) {
        findNavController().navigate(R.id.destination_fragment_sign_in)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

}
