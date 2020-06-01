package am.chamich.app.account.features.edit

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.databinding.AccountFragmentEditAccountBinding
import android.content.Context

internal class EditAccountFragment : CoreFragment<AccountFragmentEditAccountBinding>() {

    override var layoutId: Int = R.layout.account_fragment_edit_account

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    companion object {
        const val KEY_ACCOUNT_ID = "am.chamich.app.account.KEY_ACCOUNT_ID"
    }
}