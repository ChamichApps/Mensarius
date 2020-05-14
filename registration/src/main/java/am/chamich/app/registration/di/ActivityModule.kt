package am.chamich.app.registration.di

import am.chamich.app.registration.network.Authenticator
import am.chamich.app.registration.network.IAuthenticator
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesAuthenticator(): IAuthenticator = Authenticator()
}