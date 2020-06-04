package am.chamich.app.account.navigation.api

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections

internal interface INavigator {

    fun navigate(from: Fragment, direction: NavDirections)
}