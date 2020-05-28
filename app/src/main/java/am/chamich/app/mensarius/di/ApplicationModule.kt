package am.chamich.app.mensarius.di

import am.chamich.app.registration.Registration
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesRegistration() = Registration(context)

}