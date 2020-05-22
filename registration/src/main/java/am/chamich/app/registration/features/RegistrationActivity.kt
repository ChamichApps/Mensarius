package am.chamich.app.registration.features

import am.chamich.app.registration.R
import am.chamich.app.registration.di.ActivityComponent
import am.chamich.app.registration.di.ActivityModule
import am.chamich.app.registration.di.DaggerActivityComponent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

const val RESULT_SIGN_UP_SUCCESS = 10000
const val RESULT_SIGN_IN_SUCCESS = 10001
const val EXTRA_USER_ID = "am.chamich.app.registration.EXTRA_USER_ID"

class RegistrationActivity : AppCompatActivity() {

    private lateinit var progressView: View

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
    }

    private fun initializeProgressAndDisableClicks() {
        progressView = findViewById(R.id.layout_progress)
        progressView.setOnTouchListener { _, _ -> true }
    }

    fun showProgress(show: Boolean) {
        progressView.isVisible = show
    }
}
