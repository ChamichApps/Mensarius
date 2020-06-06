package am.chamich.app.account.features

import am.chamich.app.account.R
import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.di.AccountModule
import am.chamich.app.account.di.DaggerAccountComponent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.account_activity_accounts.*

internal class AccountsActivity : AppCompatActivity() {

    private lateinit var progressView: View

    val accountComponent: AccountComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAccountComponent
            .builder()
            .accountModule(AccountModule(applicationContext))
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity_accounts)

        initializeProgressAndDisableClicks()
        setupActionBar()
    }

    fun showProgress(show: Boolean) {
        progressView.isVisible = show
    }

    private fun initializeProgressAndDisableClicks() {
        progressView = findViewById(R.id.layout_progress)
        progressView.setOnTouchListener { _, _ -> true }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_accounts)
    }
}