package am.chamich.app.registration.features.signin

import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.helpers.TestCoroutineRule
import am.chamich.app.registration.model.User
import am.chamich.app.registration.model.api.IUser
import am.chamich.app.registration.network.api.IAuthenticator
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SignInViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val authenticator: IAuthenticator = mockk()
    private val viewModel: SignInViewModel = SignInViewModel(authenticator)

    @ExperimentalCoroutinesApi
    @Test
    fun when_SignInSucceed_then_UserDataReturned() {
        val observer: Observer<IUser> = mockk()
        testCoroutineRule.runBlockingTest {
            coEvery { authenticator.signIn(any(), any()) } returns USER

            viewModel.signedInUser.observeForever(observer)
            viewModel.signIn(EMAIL, PASSWORD)

            coVerify(exactly = 1) { authenticator.signIn(EMAIL, PASSWORD) }
            coVerify(exactly = 1) { observer.onChanged(USER) }

            viewModel.signedInUser.removeObserver(observer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun when_SignInFailed_then_FailureReturned() {
        val observer: Observer<Failure> = mockk()
        testCoroutineRule.runBlockingTest {
            coEvery { authenticator.signIn(any(), any()) } throws Failure.ServerError

            viewModel.signInFailure.observeForever(observer)
            viewModel.signIn(EMAIL, PASSWORD)

            coVerify(exactly = 1) { authenticator.signIn(any(), any()) }
            coVerify(exactly = 1) { observer.onChanged(Failure.ServerError) }

            viewModel.signInFailure.removeObserver(observer)
        }
    }

    private companion object {
        val USER = User(1)
        const val EMAIL = "chamich.apps@gmail.com"
        const val PASSWORD = "Test123456!"
    }
}
