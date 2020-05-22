package am.chamich.app.registration.model

import am.chamich.app.registration.model.api.IUser

data class User(
    override val id: Int
) : IUser