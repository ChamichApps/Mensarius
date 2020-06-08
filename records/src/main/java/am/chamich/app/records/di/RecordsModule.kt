package am.chamich.app.records.di

import am.chamich.app.records.database.RoomDatabase
import am.chamich.app.records.repositories.RecordsRepository
import am.chamich.app.records.repositories.api.IRecordsRepository
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class RecordsModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesRecordsRepository(): IRecordsRepository =
        RecordsRepository(
            Room.databaseBuilder(
                context, RoomDatabase::class.java, RECORDS_DATABASE_NAME
            ).build().getRecordsDatabase()
        )

    private companion object {
        const val RECORDS_DATABASE_NAME = "records.db"
    }
}