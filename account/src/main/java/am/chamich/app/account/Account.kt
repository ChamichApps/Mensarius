package am.chamich.app.account

import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.di.DaggerAccountComponent
import am.chamich.app.account.features.AccountsActivity
import am.chamich.app.account.repositories.api.IAccountsRepository
import android.app.Activity
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class Account(val context: Context) {

    private val accountComponent: AccountComponent? = DaggerAccountComponent
        .builder()
        .accountModule(am.chamich.app.account.di.AccountModule(context))
        .build()

    @Inject
    internal lateinit var repository: IAccountsRepository

    init {
        accountComponent?.inject(this)
    }

    companion object {
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, AccountsActivity::class.java))
        }
    }

}