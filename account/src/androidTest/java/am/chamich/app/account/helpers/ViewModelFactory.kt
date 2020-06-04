package am.chamich.app.account.helpers

import am.chamich.app.account.features.accounts.AccountsViewModel
import am.chamich.app.account.features.modify.add.AddAccountViewModel
import am.chamich.app.account.features.modify.edit.EditAccountViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val mockedViewModel: ViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountsViewModel::class.java) ||
            modelClass.isAssignableFrom(AddAccountViewModel::class.java) ||
            modelClass.isAssignableFrom(EditAccountViewModel::class.java)
        ) {
            return mockedViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}