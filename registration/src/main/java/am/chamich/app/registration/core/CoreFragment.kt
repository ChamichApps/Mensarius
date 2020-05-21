package am.chamich.app.registration.core

import am.chamich.app.registration.R
import am.chamich.app.registration.di.ActivityComponent
import am.chamich.app.registration.extensions.isValidEmail
import am.chamich.app.registration.extensions.isValidPassword
import am.chamich.app.registration.extensions.textAsString
import am.chamich.app.registration.features.RegistrationActivity
import am.chamich.app.registration.navigation.api.INavigator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

abstract class CoreFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var navigator: INavigator

    protected val activityComponent: ActivityComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity() as? RegistrationActivity)?.activityComponent
    }

    protected fun showProgress() {
        (requireActivity() as? RegistrationActivity)?.showProgress(true)
    }

    protected fun hideProgress() {
        (requireActivity() as? RegistrationActivity)?.showProgress(false)
    }

    protected fun isEmailInputCorrect(textInputLayout: TextInputLayout) =
        textInputLayout.editText!!.textAsString.isValidEmail.apply {
            textInputLayout.error = if (!this) getString(R.string.error_email_invalid) else null
            textInputLayout.isErrorEnabled = !this
        }

    protected fun isPasswordInputCorrect(textInputLayout: TextInputLayout) =
        textInputLayout.editText!!.textAsString.isValidPassword.apply {
            textInputLayout.error = if (!this) getString(R.string.error_password_invalid) else null
            textInputLayout.isErrorEnabled = !this
        }
}