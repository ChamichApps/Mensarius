package am.chamich.app.registration.di

import am.chamich.app.registration.navigation.Navigator
import am.chamich.app.registration.navigation.api.INavigator
import am.chamich.app.registration.network.FirebaseAuthenticator
import am.chamich.app.registration.network.api.IAuthenticator
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class RegistrationModule(
    private val context: Context
) {

    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideNavigator(): INavigator = Navigator()

    @Singleton
    @Provides
    fun providesAuthenticator(): IAuthenticator =
        FirebaseAuthenticator(context, FirebaseAuth.getInstance())
}
