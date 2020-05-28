package am.chamich.app.registration.navigation

import am.chamich.app.registration.navigation.api.INavigator
import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController

class Navigator : INavigator {

    override fun finishActivityWithResult(activity: Activity, result: Int, intent: Intent) {
        activity.setResult(result, intent)
        activity.finish()
    }

    override fun navigate(from: Fragment, to: Int) {
        findNavController(from).navigate(to)
    }
}
