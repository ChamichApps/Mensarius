package am.chamich.app.account.helpers

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher


class Matchers {

    fun a(@IdRes resourceId: Int, position: Int, text: String) {
        onView(withId(resourceId))
            .check(matches(atPosition(position, hasDescendant(withText(text)))))
    }

    fun a(@IdRes resourceId: Int, position: Int, @StringRes stringResource: Int) {
        onView(withId(resourceId))
            .check(matches(atPosition(position, hasDescendant(withText(stringResource)))))
    }

    fun isTextInPopupDisplayed(@StringRes stringResource: Int) {
        onView(withText(stringResource))
            .inRoot(isPlatformPopup())
            .check(matches(isDisplayed()))
    }

    fun viewIsDisplayedAndContainsText(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(matches(isDisplayed()))
    }

    fun viewIsDisplayedAndContainsText(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun viewIsDisplayedContainsTextAndDisabled(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(matches(not(isEnabled())))
    }


    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }


}