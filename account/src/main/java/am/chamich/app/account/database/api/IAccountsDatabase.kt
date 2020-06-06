package am.chamich.app.account.database.api

import am.chamich.app.account.database.entity.AccountEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
internal interface IAccountsDatabase {

    @Insert
    fun insertAccount(account: AccountEntity): Long

    @Update
    fun updateAccount(account: AccountEntity)

    @Query("DELETE FROM accounts WHERE account_id = :id")
    fun deleteAccount(id: Long)

    @Query("SELECT * FROM accounts WHERE account_id == :id")
    fun queryAccount(id: Long): AccountEntity

    @Query("SELECT * FROM accounts")
    fun queryAccounts(): List<AccountEntity>
}
