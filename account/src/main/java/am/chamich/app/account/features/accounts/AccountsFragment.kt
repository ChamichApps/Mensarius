package am.chamich.app.account.features.accounts

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.databinding.AccountFragmentAccountsBinding
import am.chamich.app.account.features.accounts.adapters.AccountsAdapter
import am.chamich.app.account.models.Account
import am.chamich.app.account.models.api.IAccount
import android.content.Context
import android.os.Bundle
import android.view.View

internal class AccountsFragment : CoreFragment<AccountFragmentAccountsBinding>() {

    override var layoutId: Int = R.layout.account_fragment_accounts

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    private fun onAccountClicked(account: IAccount) {

    }

    private fun initializeRecyclerView() {
        binding.recyclerviewAccounts.adapter =
            AccountsAdapter(
                listOf(Account(1, "111"), Account(2, "2222"))
            ) { selectedAccount: IAccount ->
                onAccountClicked(selectedAccount)
            }
    }
}
