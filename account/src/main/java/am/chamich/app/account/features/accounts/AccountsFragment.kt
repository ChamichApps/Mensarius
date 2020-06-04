package am.chamich.app.account.features.accounts

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.databinding.AccountFragmentAccountsBinding
import am.chamich.app.account.extensions.observe
import am.chamich.app.account.extensions.viewModel
import am.chamich.app.account.features.accounts.adapters.AccountsAdapter
import am.chamich.app.account.models.AccountModel
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
        binding.listener = ClickListeners()
        accountsViewModel.loadAccounts()
        initializeRecyclerView()
    }

    private fun onAccountClicked(id: Long) {
        navigator.navigate(this, AccountsFragmentDirections.actionToEditAccountFragment(id))
    }

    private fun initializeRecyclerView() {
        accountsAdapter = AccountsAdapter { accountId: Long -> onAccountClicked(accountId) }
        binding.recyclerviewAccounts.adapter = accountsAdapter
    }

    private fun handleLoadedAccounts(accounts: List<AccountEntity>?) {
        accountsAdapter.setAccounts(accounts?.map {
            AccountModel(it.id, it.name, it.number, it.value, it.type, it.currency, it.color)
        } ?: emptyList())
    }

    inner class ClickListeners {
        fun onAddAccountClicked() {
            navigator.navigate(
                this@AccountsFragment,
                AccountsFragmentDirections.actionToAddAccountFragment()
            )
        }
    }
}
