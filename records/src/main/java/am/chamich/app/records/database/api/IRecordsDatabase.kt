package am.chamich.app.records.database.api

import am.chamich.app.records.database.entity.RecordEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
internal interface IRecordsDatabase {

    @Insert
    fun insertRecord(record: RecordEntity): Long

    @Update
    fun updateRecord(record: RecordEntity)
}
