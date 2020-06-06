package am.chamich.app.account.features.modify.add

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

internal class AddAccountViewModel @Inject constructor(
    private val repository: IAccountsRepository
) : ViewModel() {

    private val success: MutableLiveData<Long> = MutableLiveData()
    val loadedAccount: LiveData<Long> = success

    fun saveAccount(accountEntity: AccountEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                success.postValue(repository.saveAccount(accountEntity))
            }
        }
    }
}