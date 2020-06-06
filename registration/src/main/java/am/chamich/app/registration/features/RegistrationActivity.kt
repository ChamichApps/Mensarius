package am.chamich.app.registration.features

import am.chamich.app.registration.R
import am.chamich.app.registration.di.DaggerRegistrationComponent
import am.chamich.app.registration.di.RegistrationComponent
import am.chamich.app.registration.di.RegistrationModule
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.registration_activity_registration.*

const val RESULT_SIGN_UP_SUCCESS = 10000
const val RESULT_SIGN_IN_SUCCESS = 10001
const val REQUEST_CODE_REGISTRATION = 11000
const val EXTRA_USER_ID = "am.chamich.app.registration.EXTRA_USER_ID"
const val EXTRA_USER_EMAIL = "am.chamich.app.registration.EXTRA_USER_EMAIL"
const val EXTRA_USER_NAME = "am.chamich.app.registration.EXTRA_USER_NAME"

internal class RegistrationActivity : AppCompatActivity(),
    AppBarConfiguration.OnNavigateUpListener {

    private lateinit var progressView: View
    private lateinit var navController: NavController

    val registrationComponent: RegistrationComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        if (REGISTRATION_COMPONENT == null) {
            // THIS BLOCK IS USED FOR INTEGRATION TESTS ONLY
            DaggerRegistrationComponent
                .builder()
                .registrationModule(RegistrationModule(applicationContext))
                .build()
        } else REGISTRATION_COMPONENT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_registration)

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

    fun showProgress(show: Boolean) {
        progressView.isVisible = show
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

    companion object {
        var REGISTRATION_COMPONENT: RegistrationComponent? = null
    }
}
