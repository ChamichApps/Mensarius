package am.chamich.app.account.navigation

import am.chamich.app.account.navigation.api.INavigator
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.findNavController

internal class Navigator : INavigator {

    override fun navigate(from: Fragment, direction: NavDirections) {
        findNavController(from).navigate(direction)
    }
}