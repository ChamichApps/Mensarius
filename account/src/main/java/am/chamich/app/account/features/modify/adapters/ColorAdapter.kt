package am.chamich.app.account.features.modify.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountColorBinding
import am.chamich.app.account.features.modify.adapters.api.CoreAdapter
import am.chamich.app.account.models.ColorModel
import android.content.Context
import android.view.View
import android.view.ViewGroup

internal class ColorAdapter(
    context: Context,
    private val listener: (ColorModel?) -> Unit
) : CoreAdapter<ColorModel, AccountItemAccountColorBinding>(
    context, R.layout.account_item_account_color, ColorModel.values()
) {
    override lateinit var binding: AccountItemAccountColorBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
        super.getView(position, convertView, parent).apply {
            val item = getItem(position)
            setOnClickListener { listener(item) }
            binding.accountColor = item
            tag = binding
        }
}