package am.chamich.app.account

import am.chamich.app.account.features.AccountsActivity
import android.app.Activity
import android.content.Context
import android.content.Intent

class Account(val context: Context) {


    companion object {
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, AccountsActivity::class.java))
        }
    }

}