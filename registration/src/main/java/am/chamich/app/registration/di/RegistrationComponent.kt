package am.chamich.app.registration.di

import am.chamich.app.registration.Registration
import am.chamich.app.registration.di.viewmodel.ViewModelModule
import am.chamich.app.registration.features.home.HomeFragment
import am.chamich.app.registration.features.password.RestorePasswordFragment
import am.chamich.app.registration.features.signin.SignInFragment
import am.chamich.app.registration.features.signup.SignUpFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RegistrationModule::class,
        ViewModelModule::class
    ]
)
internal interface RegistrationComponent {

    fun inject(registration: Registration)

    fun inject(fragment: HomeFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: RestorePasswordFragment)
}