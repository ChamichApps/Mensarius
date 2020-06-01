package am.chamich.app.account.database.api

import am.chamich.app.account.database.entity.AccountEntity
import androidx.room.*

@Dao
internal interface IAccountsDatabase {

    @Insert
    fun insertAccount(account: AccountEntity): Long

    @Update
    fun updateAccount(account: AccountEntity)

    @Delete
    fun deleteAccount(account: AccountEntity)

    @Query("SELECT * FROM accounts WHERE account_id == :id")
    fun queryAccount(id: Long): AccountEntity

    @Query("SELECT * FROM accounts")
    fun queryAccounts(): List<AccountEntity>
}
