package am.chamich.app.registration.features

import am.chamich.app.registration.R
import am.chamich.app.registration.di.ActivityComponent
import am.chamich.app.registration.di.ActivityModule
import am.chamich.app.registration.di.DaggerActivityComponent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(applicationContext, this))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }
}
