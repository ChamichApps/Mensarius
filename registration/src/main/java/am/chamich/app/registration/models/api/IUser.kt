package am.chamich.app.registration.models.api

internal interface IUser {
    val id: String
    val email: String?
    val name: String?
}