package am.chamich.app.account.models

internal enum class AccountType(val id: Int) {
    GENERAL(0),
    CASH(1),
    CURRENT(2),
    CREDIT(3),
    SAVING(4),
    BONUS(5),
    INSURANCE(6),
    INVESTMENT(7),
    LOAN(8),
    MORTGAGE(9),
    OVERDRAFT(10);

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}