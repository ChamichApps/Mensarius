package am.chamich.app.account.features.modify.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountColorBinding
import am.chamich.app.account.models.ColorModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter

internal class ColorAdapter(
    context: Context,
    private val listener: (ColorModel) -> Unit
) : ArrayAdapter<ColorModel>(context, R.layout.account_item_account_color, ColorModel.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val accountColor = getItem(position)
        val binding: AccountItemAccountColorBinding
        val view = if (convertView == null) {
            binding = AccountItemAccountColorBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
            binding.root
        } else {
            binding = convertView.tag as AccountItemAccountColorBinding
            convertView
        }

        view.setOnClickListener {
            listener(accountColor ?: throw IllegalArgumentException("AccountColor is null"))
        }
        binding.accountColor = accountColor
        view.tag = binding

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence) =
                FilterResults().apply {
                    values = ColorModel.values()
                    count = ColorModel.values().size
                }

            override fun publishResults(constraint: CharSequence, results: FilterResults) = Unit
        }
    }
}