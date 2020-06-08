package am.chamich.app.records.di

import am.chamich.app.account.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RecordsModule::class,
        ViewModelModule::class
    ]
)
internal interface RecordsComponent