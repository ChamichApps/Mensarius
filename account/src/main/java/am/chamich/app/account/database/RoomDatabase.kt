package am.chamich.app.account.database

import am.chamich.app.account.database.api.IAccountsDatabase
import am.chamich.app.account.database.entity.AccountEntity
import androidx.room.Database

@Database(entities = [AccountEntity::class], version = 1)
internal abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun getAccountsDatabase(): IAccountsDatabase
}