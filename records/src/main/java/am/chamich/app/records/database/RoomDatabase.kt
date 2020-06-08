package am.chamich.app.records.database

import am.chamich.app.records.database.api.IRecordsDatabase
import am.chamich.app.records.database.entity.RecordEntity
import androidx.room.Database

@Database(entities = [RecordEntity::class], version = 1)
internal abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun getRecordsDatabase(): IRecordsDatabase
}