package am.chamich.app.registration.helpers

import android.os.IBinder
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class Matchers {

    fun viewWithIdIsDisplayed(@IdRes resourceId: Int) {
        onView(withId(resourceId)).check(matches(isDisplayed()))
    }

    fun viewIsDisplayedAndContainsText(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(matches(isDisplayed()))
    }

    fun viewWithTextIsNotDisplayed(@StringRes stringResource: Int) {
        onView(withText(stringResource)).check(doesNotExist())
    }

    fun toastWithMessageIsDisplayed(@StringRes text: Int) {
        onView(withText(text)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    class ToastMatcher : TypeSafeMatcher<Root?>() {
        override fun describeTo(description: Description?) {
            description?.appendText("is toast")
        }

        override fun matchesSafely(item: Root?): Boolean {
            val type: Int? = item?.windowLayoutParams?.get()?.type
            if (type == WindowManager.LayoutParams.TYPE_TOAST) {
                val windowToken: IBinder = item.decorView.windowToken
                val appToken: IBinder = item.decorView.applicationWindowToken
                if (windowToken === appToken) {
                    return true
                }
            }
            return false
        }
    }
}