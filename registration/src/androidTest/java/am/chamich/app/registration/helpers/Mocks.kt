package am.chamich.app.registration.helpers

class Mocks {

    inline fun <reified T : Any> mock(block: T.() -> Unit = {}): T =
        io.mockk.mockk(relaxed = true, block = block)

    inline fun <reified T : Any> slot() = io.mockk.slot<T>()

    fun verify(times: Int = 1, verifyBlock: io.mockk.MockKVerificationScope.() -> Unit) {
        io.mockk.verify(exactly = times) { verifyBlock() }
        io.mockk.confirmVerified()
    }

    fun coVerify(times: Int = 1, verifyBlock: io.mockk.MockKVerificationScope.() -> Unit) {
        io.mockk.coVerify(exactly = times) { verifyBlock() }
    }

    fun <T> every(stubBlock: io.mockk.MockKMatcherScope.() -> T): io.mockk.MockKStubScope<T, T> =
        io.mockk.every { stubBlock() }
}