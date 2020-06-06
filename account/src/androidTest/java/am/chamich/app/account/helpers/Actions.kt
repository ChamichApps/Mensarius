package am.chamich.app.account.helpers

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class Actions {

    fun enterText(@IdRes viewId: Int, text: String?) {
        onView(withId(viewId)).perform(replaceText(text))
    }

    fun performClick(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    fun performClickOnItemAtPosition(@IdRes viewId: Int, position: Int) {
        onView(withId(viewId))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    fun performClickOnPopupItem(@StringRes stringResource: Int) {
        onView(withText(stringResource))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
    }


}