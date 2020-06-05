package am.chamich.app.account.features.modify.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountCurrencyBinding
import am.chamich.app.account.features.modify.adapters.api.CoreAdapter
import am.chamich.app.account.models.CurrencyModel
import android.content.Context
import android.view.View
import android.view.ViewGroup

internal class CurrencyAdapter(
    context: Context,
    private val listener: (CurrencyModel?) -> Unit
) : CoreAdapter<CurrencyModel, AccountItemAccountCurrencyBinding>(
    context, R.layout.account_item_account_currency, CurrencyModel.values()
) {
    override lateinit var binding: AccountItemAccountCurrencyBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
        super.getView(position, convertView, parent).apply {
            val item = getItem(position)
            setOnClickListener { listener(item) }
            binding.accountCurrency = item
            tag = binding
        }
}