package am.chamich.app.account.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
internal data class AccountEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    val id: Long = 0L,

    @ColumnInfo(name = "account_name")
    val name: String,

    @ColumnInfo(name = "bank_account_number")
    val number: String,

    @ColumnInfo(name = "account_type")
    val type: Int,

    @ColumnInfo(name = "initial_value")
    val value: Long,

    @ColumnInfo(name = "currency")
    val currency: Int,

    @ColumnInfo(name = "color")
    val color: Int
)