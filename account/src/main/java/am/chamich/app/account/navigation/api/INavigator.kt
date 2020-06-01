package am.chamich.app.account.navigation.api

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

internal interface INavigator {

    fun navigate(from: Fragment, @IdRes to: Int, bundle: Bundle = Bundle())
}