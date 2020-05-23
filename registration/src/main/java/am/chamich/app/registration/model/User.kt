package am.chamich.app.registration.model

import am.chamich.app.registration.model.api.IUser

data class User(
    override val id: String,
    override val email: String?,
    override val name: String?
) : IUser

data class UnknownUser(
    override val id: String = "",
    override val email: String? = null,
    override val name: String? = null
) : IUser