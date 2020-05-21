package am.chamich.app.registration.navigation.api

import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface INavigator {

    fun finishActivityWithResult(result: Int, intent: Intent = Intent())

    fun navigate(from: Fragment, @IdRes to: Int)
}