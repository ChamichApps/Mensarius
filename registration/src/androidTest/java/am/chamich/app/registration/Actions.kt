package am.chamich.app.registration

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId

class Actions {

    fun enterText(@IdRes viewId: Int, text: String?) {
        onView(withId(viewId)).perform(replaceText(text))
    }

    fun perfoemClick(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }
}