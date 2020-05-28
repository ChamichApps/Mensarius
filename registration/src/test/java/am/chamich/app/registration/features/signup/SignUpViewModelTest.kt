package am.chamich.app.registration.features.signup

import am.chamich.app.registration.exceptions.Failure
import am.chamich.app.registration.helpers.TestCoroutineRule
import am.chamich.app.registration.models.User
import am.chamich.app.registration.models.api.IUser
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
class SignUpViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val authenticator: IAuthenticator = mockk()
    private val viewModel: SignUpViewModel = SignUpViewModel(authenticator)

    @ExperimentalCoroutinesApi
    @Test
    fun when_SignUpSucceed_then_UserDataReturned() {
        val observer: Observer<IUser> = mockk()
        testCoroutineRule.runBlockingTest {
            coEvery { authenticator.signUp(any(), any()) } returns USER

            viewModel.signedUpUser.observeForever(observer)
            viewModel.signUp(EMAIL, PASSWORD)

            coVerify(exactly = 1) { authenticator.signUp(EMAIL, PASSWORD) }
            coVerify(exactly = 1) { observer.onChanged(USER) }

            viewModel.signedUpUser.removeObserver(observer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun when_SignUpFailed_then_FailureReturned() {
        val observer: Observer<Failure> = mockk()
        testCoroutineRule.runBlockingTest {
            coEvery { authenticator.signUp(any(), any()) } throws EXCEPTION

            viewModel.signUpFailure.observeForever(observer)
            viewModel.signUp(EMAIL, PASSWORD)

            coVerify(exactly = 1) { authenticator.signUp(any(), any()) }
            coVerify(exactly = 1) { observer.onChanged(EXCEPTION) }

            viewModel.signUpFailure.removeObserver(observer)
        }
    }

    private companion object {
        const val USER_NAME = "Chamich Apps"
        const val EMAIL = "chamich.apps@gmail.com"
        const val PASSWORD = "Test123456!"
        val USER = User("9cfb4401-2eb8-43c3-8adf-4c153dc97c4d", EMAIL, USER_NAME)
        val EXCEPTION = Failure.SignUpException("Unable to Sign Up, please try again")
    }
}