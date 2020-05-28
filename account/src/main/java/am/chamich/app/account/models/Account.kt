package am.chamich.app.account.models

import am.chamich.app.account.models.api.IAccount

internal data class Account(
    override val id: Long,
    override val name: String
) : IAccount