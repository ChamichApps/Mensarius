package am.chamich.app.account.models

import am.chamich.app.account.models.api.IAccountModel

internal data class AccountModel(
    override val id: Long,
    override val name: String,
    override val number: String,
    override val value: Long,
    override val type: Int,
    override val currency: Int,
    override val color: Int
) : IAccountModel

internal data class DefaultAccountModel(
    override val id: Long = 0,
    override val name: String = "",
    override val number: String = "",
    override val value: Long = 0,
    override val type: Int = 0,
    override val currency: Int = 0,
    override val color: Int = 0
) : IAccountModel