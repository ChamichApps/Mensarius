package am.chamich.app.registration.di.viewmodel

import am.chamich.app.registration.features.signin.SignInViewModel
import am.chamich.app.registration.features.signup.SignUpViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindsSignInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindsSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel
}
