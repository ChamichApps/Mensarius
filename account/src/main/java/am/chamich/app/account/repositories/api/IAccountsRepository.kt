package am.chamich.app.account.repositories.api

import am.chamich.app.account.database.entity.AccountEntity

internal interface IAccountsRepository {

    suspend fun loadAccounts(): List<AccountEntity>

    suspend fun loadAccount(id: Long): AccountEntity

    suspend fun saveAccount(accountEntity: AccountEntity): Long

    suspend fun deleteAccount(id: Long)

    suspend fun updateAccount(accountEntity: AccountEntity)
}