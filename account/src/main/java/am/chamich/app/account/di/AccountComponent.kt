package am.chamich.app.account.di

import am.chamich.app.account.di.viewmodel.ViewModelModule
import am.chamich.app.account.features.accounts.AccountsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AccountModule::class,
        ViewModelModule::class
    ]
)
internal interface AccountComponent {

    fun inject(fragment: AccountsFragment)
}