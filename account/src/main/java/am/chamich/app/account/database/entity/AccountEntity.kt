package am.chamich.app.account.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    private val id: Long,

    @ColumnInfo(name = "account_name")
    private val accountName: String,

    @ColumnInfo(name = "bank_account_number")
    private val accountNumber: String,

    @ColumnInfo(name = "account_type")
    private val accountType: Int,

    @ColumnInfo(name = "initial_value")
    private val initialValue: Long,

    @ColumnInfo(name = "currency")
    private val currency: Int,

    @ColumnInfo(name = "color")
    private val color: Int
)