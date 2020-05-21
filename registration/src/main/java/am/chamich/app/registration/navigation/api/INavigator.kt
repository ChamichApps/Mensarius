package am.chamich.app.registration.navigation.api

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface INavigator {

    fun finishActivityWithResult(result: Int)

    fun navigate(from: Fragment, @IdRes to: Int)
}