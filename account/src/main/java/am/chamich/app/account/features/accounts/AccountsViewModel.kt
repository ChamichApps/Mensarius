package am.chamich.app.account.features.accounts

import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.repositories.api.IAccountsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AccountsViewModel @Inject constructor(
    private val repository: IAccountsRepository
) : ViewModel() {

    private val success: MutableLiveData<List<AccountEntity>> = MutableLiveData()
    val loadedAccounts: LiveData<List<AccountEntity>> = success

    fun loadAccounts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                success.postValue(repository.loadAccounts())
            }
        }
    }

}