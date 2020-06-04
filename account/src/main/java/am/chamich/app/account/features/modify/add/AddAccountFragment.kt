package am.chamich.app.account.features.modify.add

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.databinding.AccountFragmentAddAccountBinding
import android.content.Context

internal class AddAccountFragment : CoreFragment<AccountFragmentAddAccountBinding>() {

    override var layoutId: Int = R.layout.account_fragment_add_account

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }
}