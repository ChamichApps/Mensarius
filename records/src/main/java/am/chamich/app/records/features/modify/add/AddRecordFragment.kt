package am.chamich.app.records.features.modify.add

import am.chamich.app.records.R
import am.chamich.app.records.core.CoreFragment
import am.chamich.app.records.databinding.RecordsFragmentAddRecordBinding
import android.content.Context

internal class AddRecordFragment : CoreFragment<RecordsFragmentAddRecordBinding>() {

    override var layoutId = R.layout.records_fragment_add_record

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recordsComponent?.inject(this)
    }
}