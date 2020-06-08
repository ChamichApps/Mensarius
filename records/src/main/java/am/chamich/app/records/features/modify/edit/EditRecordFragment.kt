package am.chamich.app.records.features.modify.edit

import am.chamich.app.records.R
import am.chamich.app.records.core.CoreFragment
import am.chamich.app.records.databinding.RecordsFragmentEditRecordBinding
import android.content.Context

internal class EditRecordFragment : CoreFragment<RecordsFragmentEditRecordBinding>() {

    override var layoutId = R.layout.records_fragment_edit_record

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recordsComponent?.inject(this)
    }
}