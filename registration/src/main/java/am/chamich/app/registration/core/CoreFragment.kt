package am.chamich.app.registration.core

import am.chamich.app.registration.R
import am.chamich.app.registration.di.RegistrationComponent
import am.chamich.app.registration.extensions.isValidEmail
import am.chamich.app.registration.extensions.isValidPassword
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.features.RegistrationActivity
import am.chamich.app.registration.navigation.api.INavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

internal abstract class CoreFragment<T : ViewDataBinding> : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var navigator: INavigator

    protected lateinit var binding: T
    protected var toast: Toast? = null

    protected abstract var layoutId: Int

    protected val registrationComponent: RegistrationComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity() as? RegistrationActivity)?.registrationComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        toast?.cancel()
    }

    protected fun showProgress() {
        (requireActivity() as? RegistrationActivity)?.showProgress(true)
    }

    protected fun hideProgress() {
        (requireActivity() as? RegistrationActivity)?.showProgress(false)
    }

    // TODO: Move this method to extension
    protected fun isEmailInputCorrect(textInputLayout: TextInputLayout) =
        textInputLayout.editText!!.textAsString.isValidEmail.apply {
            textInputLayout.error =
                if (!this) getString(R.string.registration_error_email_invalid) else null
            textInputLayout.isErrorEnabled = !this
        }

    // TODO: Move this method to extension
    protected fun isPasswordInputCorrect(textInputLayout: TextInputLayout) =
        textInputLayout.editText!!.textAsString.isValidPassword.apply {
            textInputLayout.error =
                if (!this) getString(R.string.registration_error_password_invalid) else null
            textInputLayout.isErrorEnabled = !this
        }
}