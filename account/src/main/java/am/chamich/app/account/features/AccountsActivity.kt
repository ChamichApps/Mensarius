package am.chamich.app.account.features

import am.chamich.app.account.R
import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.di.AccountModule
import am.chamich.app.account.di.DaggerAccountComponent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

internal class AccountsActivity : AppCompatActivity() {

    val accountComponent: AccountComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAccountComponent
            .builder()
            .accountModule(AccountModule(this))
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity_accounts)
    }
}