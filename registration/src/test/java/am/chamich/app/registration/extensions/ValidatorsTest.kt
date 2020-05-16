package am.chamich.app.registration.extensions

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class ValidatorsTest {

    @Test
    fun when_PasswordProvided_then_ValidatorBehavesCorrect() {
        assertThat("Test1234!".isValidPassword, `is`(true))
        assertThat("Test1!".isValidPassword, `is`(true))
        assertThat("TEST1234!".isValidPassword, `is`(true))
        assertThat("test1234".isValidPassword, `is`(false))
        assertThat("Test124".isValidPassword, `is`(false))
        assertThat("T1!".isValidPassword, `is`(false))
        assertThat("!Test".isValidPassword, `is`(false))
        assertThat("".isValidPassword, `is`(false))
    }

    @Test
    fun when_EmailProvided_then_ValidatorBehavesCorrect() {
        assertThat("chamich.apps@gmail.com".isValidEmail, `is`(true))
        assertThat("Chamich.Apps@gmail.com".isValidEmail, `is`(true))
        assertThat("chamich.apps@".isValidEmail, `is`(false))
        assertThat("chamich.apps@gmail".isValidEmail, `is`(false))
        assertThat("chamich.apps.com".isValidEmail, `is`(false))
        assertThat("c!amic!.a@pps.gmail.com".isValidEmail, `is`(false))
        assertThat("c@mich.@pps@gmail.com".isValidEmail, `is`(false))
    }
}
