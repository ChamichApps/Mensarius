package am.chamich.app.records.features

import am.chamich.app.records.R
import am.chamich.app.records.di.DaggerRecordsComponent
import am.chamich.app.records.di.RecordsComponent
import am.chamich.app.records.di.RecordsModule
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

internal class RecordsActivity : AppCompatActivity() {

    val recordsComponent: RecordsComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerRecordsComponent
            .builder()
            .recordsModule(RecordsModule(applicationContext))
            .build()
    }

    // TODO: Read https://developer.android.com/guide/navigation/navigation-programmatic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.records_activity_records)
    }
}