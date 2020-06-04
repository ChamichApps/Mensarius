package am.chamich.app.account.di

import am.chamich.app.account.di.viewmodel.ViewModelModule
import am.chamich.app.account.features.accounts.AccountsFragment
import am.chamich.app.account.features.modify.add.AddAccountFragment
import am.chamich.app.account.features.modify.edit.EditAccountFragment
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
    fun inject(fragment: AddAccountFragment)
    fun inject(fragment: EditAccountFragment)
}