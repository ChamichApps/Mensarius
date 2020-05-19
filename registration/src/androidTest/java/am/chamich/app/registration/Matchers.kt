package am.chamich.app.registration

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

class Matchers {

    fun viewIsDisplayedAndContainsText(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(matches(isDisplayed()))
    }

    fun viewWithTextIsNotDisplayed(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(doesNotExist())
    }
}