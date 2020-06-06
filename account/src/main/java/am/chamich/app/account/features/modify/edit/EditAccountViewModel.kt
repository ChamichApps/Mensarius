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

    private val successLoaded: MutableLiveData<AccountEntity> = MutableLiveData()
    val loadedAccount: LiveData<AccountEntity> = successLoaded
    private val successDeleted: MutableLiveData<Unit> = MutableLiveData()
    val deletedAccount: LiveData<Unit> = successDeleted
    private val successUpdated: MutableLiveData<Unit> = MutableLiveData()
    val updatedAccount: LiveData<Unit> = successUpdated

    fun loadAccount(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                successLoaded.postValue(repository.loadAccount(id))
            }
        }
    }

    fun deleteAccount(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                successDeleted.postValue(repository.deleteAccount(id))
            }
        }
    }

    fun updateAccount(accountEntity: AccountEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                successUpdated.postValue(repository.updateAccount(accountEntity))
            }
        }
    }

}