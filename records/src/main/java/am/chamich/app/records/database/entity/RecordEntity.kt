package am.chamich.app.records.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
internal data class RecordEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val id: Long = 0L,

    @ColumnInfo(name = "category")
    val category: Int,

    @ColumnInfo(name = "amount")
    val amount: Long,

    @ColumnInfo(name = "currency")
    val currency: Int,

    @ColumnInfo(name = "note")
    val note: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "account_type")
    val accountType: Int,

    @ColumnInfo(name = "record_type")
    val recordType: Int
)