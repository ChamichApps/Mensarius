package am.chamich.app.mensarius

import am.chamich.app.mensarius.di.ApplicationComponent
import am.chamich.app.mensarius.di.ApplicationModule
import am.chamich.app.mensarius.di.DaggerApplicationComponent
import android.app.Application

class MensariusApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}