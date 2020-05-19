package am.chamich.app.registration.features.password

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.RestorePasswordFragmentBinding
import am.chamich.app.registration.extensions.textAsString
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class RestorePasswordFragment : CoreFragment() {

    private lateinit var binding: RestorePasswordFragmentBinding
    private lateinit var viewModel: RestorePasswordViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.restore_password_fragment, container, false
        )
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RestorePasswordViewModel::class.java]
    }

    fun onPasswordRestoreClicked() {
        if (isEmailInputCorrect(binding.textInputLayoutEmail)) {
            viewModel.restorePassword(binding.editTextEmail.textAsString)
        }
    }

    private fun inject() {
        activityComponent?.inject(this)
    }
}
