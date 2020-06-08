package am.chamich.app.records.di

import am.chamich.app.account.di.viewmodel.ViewModelModule
import am.chamich.app.records.features.modify.add.AddRecordFragment
import am.chamich.app.records.features.modify.edit.EditRecordFragment
import am.chamich.app.records.features.records.RecordsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RecordsModule::class,
        ViewModelModule::class
    ]
)
internal interface RecordsComponent {

    fun inject(fragment: RecordsFragment)
    fun inject(fragment: AddRecordFragment)
    fun inject(fragment: EditRecordFragment)
}