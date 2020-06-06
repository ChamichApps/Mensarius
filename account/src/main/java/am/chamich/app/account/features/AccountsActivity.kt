package am.chamich.app.account.features

import am.chamich.app.account.R
import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.di.AccountModule
import am.chamich.app.account.di.DaggerAccountComponent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.account_activity_accounts.*

internal class AccountsActivity : AppCompatActivity() {

    val accountComponent: AccountComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAccountComponent
            .builder()
            .accountModule(AccountModule(applicationContext))
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity_accounts)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_accounts)
    }
}