package am.chamich.app.registration.models

import am.chamich.app.registration.models.api.IUser

internal data class User(
    override val id: String,
    override val email: String?,
    override val name: String?
) : IUser

internal data class UnknownUser(
    override val id: String = "",
    override val email: String? = null,
    override val name: String? = null
) : IUser