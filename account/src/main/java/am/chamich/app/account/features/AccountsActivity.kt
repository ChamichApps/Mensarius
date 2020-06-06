package am.chamich.app.account.features

import am.chamich.app.account.R
import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.di.AccountModule
import am.chamich.app.account.di.DaggerAccountComponent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_activity_accounts.*

internal class AccountsActivity : AppCompatActivity(), AppBarConfiguration.OnNavigateUpListener {

    private lateinit var progressView: View
    private lateinit var navController: NavController

    val accountComponent: AccountComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        if (ACCOUNT_COMPONENT == null) {
            // THIS BLOCK IS USED FOR INTEGRATION TESTS ONLY
            DaggerAccountComponent
                .builder()
                .accountModule(AccountModule(applicationContext))
                .build()
        } else ACCOUNT_COMPONENT
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity_accounts)

        initializeProgressAndDisableClicks()
        setupNavController()
        setupActionBar()
    }

    override fun onSupportNavigateUp() =
        if (R.id.destination_fragment_accounts == navController.currentDestination?.id) {
            finish()
            false
        } else {
            navController.navigateUp()
        }

    fun showProgress(show: Boolean) {
        progressView.isVisible = show
    }

    private fun initializeProgressAndDisableClicks() {
        progressView = findViewById(R.id.layout_progress)
        progressView.setOnTouchListener { _, _ -> true }
    }

    private fun setupNavController() {
        navController = findNavController(R.id.nav_host_fragment_accounts)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_accounts)
        setupActionBarWithNavController(
            navController, AppBarConfiguration.Builder()
                .setFallbackOnNavigateUpListener(this)
                .build()
        )
    }

    companion object {
        var ACCOUNT_COMPONENT: AccountComponent? = null
    }
}