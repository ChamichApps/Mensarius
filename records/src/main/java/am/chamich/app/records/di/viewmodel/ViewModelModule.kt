package am.chamich.app.account.di.viewmodel

import am.chamich.app.records.features.modify.add.AddRecordViewModel
import am.chamich.app.records.features.modify.edit.EditRecordViewModel
import am.chamich.app.records.features.records.RecordsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecordsViewModel::class)
    abstract fun bindsRecordsViewModel(viewModel: RecordsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddRecordViewModel::class)
    abstract fun bindsAddRecordsViewModel(viewModel: AddRecordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditRecordViewModel::class)
    abstract fun bindsEditRecordsViewModel(viewModel: EditRecordViewModel): ViewModel
}
