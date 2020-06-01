package am.chamich.app.account.repositories

import am.chamich.app.account.database.api.IAccountsDatabase
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.repositories.api.IAccountsRepository

internal class AccountsRepository(
    private val accountsDatabase: IAccountsDatabase
) : IAccountsRepository {

    override suspend fun loadAccounts(): List<AccountEntity> {
        return accountsDatabase.queryAccounts()
    }

}