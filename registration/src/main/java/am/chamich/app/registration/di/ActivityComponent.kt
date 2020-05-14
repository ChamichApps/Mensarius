package am.chamich.app.registration.di

import am.chamich.app.registration.di.viewmodel.ViewModelModule
import am.chamich.app.registration.features.signin.SignInFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface ActivityComponent {

    fun inject(fragment: SignInFragment)
}