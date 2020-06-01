package am.chamich.app.account.di.viewmodel

import am.chamich.app.account.features.accounts.AccountsViewModel
import am.chamich.app.account.features.add.AddAccountViewModel
import am.chamich.app.account.features.edit.EditAccountViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    abstract fun bindsAccountsViewModel(viewModel: AccountsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAccountViewModel::class)
    abstract fun bindsAddAccountViewModel(viewModel: AddAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditAccountViewModel::class)
    abstract fun bindsEditAccountViewModel(viewModel: EditAccountViewModel): ViewModel
}
