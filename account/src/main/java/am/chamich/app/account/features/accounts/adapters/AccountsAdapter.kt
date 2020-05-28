package am.chamich.app.account.features.accounts.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountBinding
import am.chamich.app.account.models.api.IAccount
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

internal class AccountsAdapter(
    private val accounts: List<IAccount>,
    private val listener: (IAccount) -> Unit
) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder =
        AccountsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.account_item_account,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = accounts.size

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val account = accounts[position]
        holder.binding.account = account
        holder.binding.root.setOnClickListener { listener(account) }
    }

    class AccountsViewHolder(
        val binding: AccountItemAccountBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
