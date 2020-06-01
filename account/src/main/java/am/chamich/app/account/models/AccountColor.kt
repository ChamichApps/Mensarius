package am.chamich.app.account.models

internal enum class AccountColor(val id: Int) {

    RED(1);

    companion object {
        fun from(id: Int) = AccountColor.values().first { it.id == id }
    }
}