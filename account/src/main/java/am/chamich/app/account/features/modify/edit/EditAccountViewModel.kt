package am.chamich.app.account.features.modify.edit

import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.repositories.api.IAccountsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class EditAccountViewModel @Inject constructor(
    private val repository: IAccountsRepository
) : ViewModel() {

    private val success: MutableLiveData<AccountEntity> = MutableLiveData()
    val loadedAccount: LiveData<AccountEntity> = success

    fun loadAccount(id: Long) {

    }

}