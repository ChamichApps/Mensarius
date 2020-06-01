package am.chamich.app.account.features.accounts

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.databinding.AccountFragmentAccountsBinding
import am.chamich.app.account.extensions.asColorResource
import am.chamich.app.account.extensions.asStringResource
import am.chamich.app.account.extensions.observe
import am.chamich.app.account.extensions.viewModel
import am.chamich.app.account.features.accounts.adapters.AccountsAdapter
import am.chamich.app.account.features.edit.EditAccountFragment.Companion.KEY_ACCOUNT_ID
import am.chamich.app.account.models.AccountColor
import am.chamich.app.account.models.AccountType
import android.content.Context
import android.os.Bundle
import android.view.View

internal class AccountsFragment : CoreFragment<AccountFragmentAccountsBinding>() {

    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var accountsAdapter: AccountsAdapter

    override var layoutId: Int = R.layout.account_fragment_accounts

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accountsViewModel = viewModel(viewModelFactory) {
            observe(loadedAccounts, ::handleLoadedAccounts)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountsViewModel.loadAccounts()
        initializeRecyclerView()
    }

    private fun onAccountClicked(id: Long) {
        navigator.navigate(this, R.id.destination_fragment_edit_account, Bundle().apply {
            putLong(KEY_ACCOUNT_ID, id)
        })
    }

    private fun initializeRecyclerView() {
        accountsAdapter = AccountsAdapter { accountId: Long -> onAccountClicked(accountId) }
        binding.recyclerviewAccounts.adapter = accountsAdapter
    }

    private fun handleLoadedAccounts(accounts: List<AccountEntity>?) {
        accountsAdapter.setAccounts(accounts?.map { accountEntity ->
            AccountsAdapter.AccountUi(
                accountEntity.id,
                accountEntity.name,
                AccountType.from(accountEntity.type).asStringResource,
                AccountColor.from(accountEntity.color).asColorResource
            )
        } ?: emptyList())
    }
}
