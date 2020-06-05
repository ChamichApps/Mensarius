package am.chamich.app.account.features.modify.add

import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.repositories.api.IAccountsRepository
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class AddAccountViewModel @Inject constructor(
    private val repository: IAccountsRepository
) : ViewModel() {

    fun saveAccount(accountEntity: AccountEntity) {

    }
}