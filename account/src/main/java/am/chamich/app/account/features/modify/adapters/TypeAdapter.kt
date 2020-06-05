package am.chamich.app.account.features.modify.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountTypeBinding
import am.chamich.app.account.features.modify.adapters.api.CoreAdapter
import am.chamich.app.account.models.TypeModel
import android.content.Context
import android.view.View
import android.view.ViewGroup

internal class TypeAdapter(
    context: Context,
    private val listener: (TypeModel?) -> Unit
) : CoreAdapter<TypeModel, AccountItemAccountTypeBinding>(
    context, R.layout.account_item_account_type, TypeModel.values()
) {
    override lateinit var binding: AccountItemAccountTypeBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
        super.getView(position, convertView, parent).apply {
            val item = getItem(position)
            setOnClickListener { listener(item) }
            binding.accountType = item
            tag = binding
        }
}
