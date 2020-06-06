package am.chamich.app.account.features.modify.edit

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

internal class EditAccountViewModel @Inject constructor(
    private val repository: IAccountsRepository
) : ViewModel() {

    private val success: MutableLiveData<AccountEntity> = MutableLiveData()
    val loadedAccount: LiveData<AccountEntity> = success

    fun loadAccount(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                success.postValue(repository.loadAccount(id))
            }
        }
    }

    fun deleteAccount(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteAccount(id)
            }
        }
    }

    fun updateAccount(accountEntity: AccountEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateAccount(accountEntity)
            }
        }
    }

}