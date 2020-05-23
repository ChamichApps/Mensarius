package am.chamich.app.registration.features

import am.chamich.app.registration.R
import am.chamich.app.registration.di.ActivityComponent
import am.chamich.app.registration.di.ActivityModule
import am.chamich.app.registration.di.DaggerActivityComponent
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_registration.*

const val RESULT_SIGN_UP_SUCCESS = 10000
const val RESULT_SIGN_IN_SUCCESS = 10001
const val REQUEST_CODE_REGISTRATION = 11000
const val EXTRA_USER_ID = "am.chamich.app.registration.EXTRA_USER_ID"
const val EXTRA_USER_EMAIL = "am.chamich.app.registration.EXTRA_USER_EMAIL"
const val EXTRA_USER_NAME = "am.chamich.app.registration.EXTRA_USER_NAME"

class RegistrationActivity : AppCompatActivity(), AppBarConfiguration.OnNavigateUpListener {

    private lateinit var progressView: View
    private lateinit var navController: NavController

    val activityComponent: ActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(applicationContext, this))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initializeProgressAndDisableClicks()
        setupNavController()
        setupActionBar()
    }

    override fun onSupportNavigateUp() =
        if (R.id.destination_fragment_home == navController.currentDestination?.id) {
            finish()
            false
        } else {
            navController.navigateUp()
        }

    private fun initializeProgressAndDisableClicks() {
        progressView = findViewById(R.id.layout_progress)
        progressView.setOnTouchListener { _, _ -> true }
    }

    private fun setupNavController() {
        navController = findNavController(R.id.nav_host_fragment_registration)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_registration)
        setupActionBarWithNavController(
            navController, AppBarConfiguration.Builder()
                .setFallbackOnNavigateUpListener(this)
                .build()
        )
    }

    fun showProgress(show: Boolean) {
        progressView.isVisible = show
    }

    companion object {
        fun startActivityForResult(activity: Activity) {
            activity.startActivityForResult(
                Intent(activity, RegistrationActivity::class.java), REQUEST_CODE_REGISTRATION
            )
        }
    }
}
