package am.chamich.app.account.repositories.api

import am.chamich.app.account.database.entity.AccountEntity

internal interface IAccountsRepository {

    suspend fun loadAccounts(): List<AccountEntity>
}