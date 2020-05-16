package am.chamich.app.registration.core

import am.chamich.app.registration.di.ActivityComponent
import am.chamich.app.registration.features.RegistrationActivity
import androidx.fragment.app.Fragment

abstract class CoreFragment : Fragment() {

    protected val activityComponent: ActivityComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity() as? RegistrationActivity)?.activityComponent
    }
}