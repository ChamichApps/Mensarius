package am.chamich.app.account.features.accounts.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountBinding
import am.chamich.app.account.models.AccountModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

internal class AccountsAdapter(
    private val listener: (Long) -> Unit
) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>() {

    private val accountModels: MutableList<AccountModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder =
        AccountsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.account_item_account,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = accountModels.size

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val accountModel = accountModels[position]
        holder.binding.accountModel = accountModel
        holder.binding.root.setOnClickListener { listener(accountModel.id) }
    }

    fun setAccounts(accounts: List<AccountModel>) {
        this.accountModels.addAll(accounts)
        this.notifyDataSetChanged()
    }

    class AccountsViewHolder(
        val binding: AccountItemAccountBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
