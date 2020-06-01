package am.chamich.app.account.navigation

import am.chamich.app.account.navigation.api.INavigator
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController

internal class Navigator : INavigator {

    override fun navigate(from: Fragment, @IdRes to: Int, bundle: Bundle) {
        findNavController(from).navigate(to, bundle)
    }
}