package am.chamich.app.mensarius.di

import am.chamich.app.mensarius.features.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(activity: HomeActivity)
}