package am.chamich.app.registration.features.home

import am.chamich.app.registration.R
import am.chamich.app.registration.core.CoreFragment
import am.chamich.app.registration.databinding.RegistrationFragmentHomeBinding
import am.chamich.app.registration.extensions.viewModel
import android.content.Context
import android.os.Bundle
import android.view.View

class HomeFragment : CoreFragment<RegistrationFragmentHomeBinding>() {

    private lateinit var viewModelHome: HomeViewModel

    override var layoutId: Int = R.layout.registration_fragment_home

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelHome = viewModel(viewModelFactory) {
            // Not observing anything.
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = ClickListeners()
    }

    inner class ClickListeners {
        fun onSignInClicked() {
            navigator.navigate(this@HomeFragment, R.id.destination_fragment_sign_in)
        }
    }
}
