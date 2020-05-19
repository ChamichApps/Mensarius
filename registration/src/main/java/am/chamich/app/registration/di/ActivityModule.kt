package am.chamich.app.registration.di

import am.chamich.app.registration.network.Authenticator
import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun providesAuthenticator(): IAuthenticator = Authenticator(context)
}
