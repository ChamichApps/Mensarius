package am.chamich.app.registration

import am.chamich.app.registration.di.DaggerRegistrationComponent
import am.chamich.app.registration.di.RegistrationComponent
import am.chamich.app.registration.di.RegistrationModule
import am.chamich.app.registration.features.REQUEST_CODE_REGISTRATION
import am.chamich.app.registration.features.RegistrationActivity
import am.chamich.app.registration.network.api.IAuthenticator
import android.app.Activity
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class Registration(context: Context) {

    private val registrationComponent: RegistrationComponent? = DaggerRegistrationComponent
        .builder()
        .registrationModule(RegistrationModule(context))
        .build()

    @Inject
    internal lateinit var authenticator: IAuthenticator

    init {
        registrationComponent?.inject(this)
    }

    fun startRegistrationActivityForResult(activity: Activity) {
        RegistrationActivity.REGISTRATION_COMPONENT = registrationComponent
        activity.startActivityForResult(
            Intent(activity, RegistrationActivity::class.java),
            REQUEST_CODE_REGISTRATION
        )
    }

    fun isSignedIn() = authenticator.isSignedIn()

    fun signOut() {
        authenticator.signOut()
    }
}
