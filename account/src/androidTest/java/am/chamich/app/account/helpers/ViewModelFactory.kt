package am.chamich.app.account.helpers

import am.chamich.app.account.features.accounts.AccountsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val mockedViewModel: ViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountsViewModel::class.java)) {
            return mockedViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}