package am.chamich.app.account.features.accounts.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

internal class AccountsAdapter(
    private val listener: (Long) -> Unit
) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>() {

    private val accounts: MutableList<AccountUi> = mutableListOf()

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
        holder.binding.root.setOnClickListener { listener(account.id) }
    }

    fun setAccounts(accounts: List<AccountUi>) {
        this.accounts.addAll(accounts)
        this.notifyDataSetChanged()
    }

    class AccountsViewHolder(
        val binding: AccountItemAccountBinding
    ) : RecyclerView.ViewHolder(binding.root)

    data class AccountUi(
        val id: Long,
        val name: String,
        @StringRes val type: Int,
        @ColorInt val color: Int
    )
}
