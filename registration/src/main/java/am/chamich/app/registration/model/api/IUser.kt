package am.chamich.app.registration.model.api

internal interface IUser {
    val id: String
    val email: String?
    val name: String?
}