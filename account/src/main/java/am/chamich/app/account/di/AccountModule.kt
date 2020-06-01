package am.chamich.app.account.di

import am.chamich.app.account.database.RoomDatabase
import am.chamich.app.account.navigation.Navigator
import am.chamich.app.account.navigation.api.INavigator
import am.chamich.app.account.repositories.AccountsRepository
import am.chamich.app.account.repositories.api.IAccountsRepository
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AccountModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesAccountsRepository(): IAccountsRepository =
        AccountsRepository(
            Room.databaseBuilder(
                context, RoomDatabase::class.java, ACCOUNTS_DATABASE_NAME
            ).build().getAccountsDatabase()
        )

    @Singleton
    @Provides
    fun providesNavigator(): INavigator = Navigator()


    private companion object {
        const val ACCOUNTS_DATABASE_NAME = "accounts.db"
    }
}