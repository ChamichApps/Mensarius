package am.chamich.app.registration

import am.chamich.app.registration.network.IAuthenticator

class MockedAuthenticator : IAuthenticator {

    override fun signIn(email: String, password: String) = Unit

    override fun signUp(email: String, password: String) = Unit

    override fun restorePassword(email: String) = Unit
}