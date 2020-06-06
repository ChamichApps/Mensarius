package am.chamich.app.account.repositories

import am.chamich.app.account.database.api.IAccountsDatabase
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.repositories.api.IAccountsRepository

internal class AccountsRepository(
    private val accountsDatabase: IAccountsDatabase
) : IAccountsRepository {

    override suspend fun loadAccounts() = accountsDatabase.queryAccounts()

    override suspend fun loadAccount(id: Long) = accountsDatabase.queryAccount(id)

    override suspend fun saveAccount(accountEntity: AccountEntity) =
        accountsDatabase.insertAccount(accountEntity)

    override suspend fun deleteAccount(id: Long) =
        accountsDatabase.deleteAccount(id)


    override suspend fun updateAccount(accountEntity: AccountEntity) =
        accountsDatabase.updateAccount(accountEntity)
}