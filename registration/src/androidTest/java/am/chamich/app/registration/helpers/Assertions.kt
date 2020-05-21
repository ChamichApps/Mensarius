package am.chamich.app.registration.helpers

import org.hamcrest.Matcher

class Assertions {

    fun <T> assertThat(actual: T, matcher: Matcher<T>) {
        org.hamcrest.MatcherAssert.assertThat(actual, matcher)
    }

}