package am.chamich.app.registration.features.password

import am.chamich.app.registration.R
import am.chamich.app.registration.databinding.RestorePasswordFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class RestorePasswordFragment : Fragment() {

    private lateinit var binding: RestorePasswordFragmentBinding
    private lateinit var viewModel: RestorePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.restore_password_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestorePasswordViewModel::class.java)
    }

    fun onPasswordRestoreClicked(view: View) {

    }

}
