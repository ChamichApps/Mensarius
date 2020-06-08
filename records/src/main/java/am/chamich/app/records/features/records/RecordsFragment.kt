package am.chamich.app.records.features.records

import am.chamich.app.records.R
import am.chamich.app.records.core.CoreFragment
import am.chamich.app.records.databinding.RecordsFragmentRecordsBinding
import android.content.Context

internal class RecordsFragment : CoreFragment<RecordsFragmentRecordsBinding>() {

    override var layoutId = R.layout.records_fragment_records

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recordsComponent?.inject(this)
    }
}