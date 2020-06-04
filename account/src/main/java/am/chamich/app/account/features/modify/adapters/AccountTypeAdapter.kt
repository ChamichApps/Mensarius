package am.chamich.app.account.features.modify.adapters

import am.chamich.app.account.R
import am.chamich.app.account.databinding.AccountItemAccountTypeBinding
import am.chamich.app.account.models.TypeModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter

internal class AccountTypeAdapter(
    context: Context,
    private val listener: (TypeModel) -> Unit
) : ArrayAdapter<TypeModel>(context, R.layout.account_item_account_type, TypeModel.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val accountType = getItem(position)
        val binding: AccountItemAccountTypeBinding
        val view = if (convertView == null) {
            binding = AccountItemAccountTypeBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
            binding.root
        } else {
            binding = convertView.tag as AccountItemAccountTypeBinding
            convertView
        }

        view.setOnClickListener {
            listener(accountType ?: throw IllegalArgumentException("AccountType is null"))
        }
        binding.accountType = accountType
        view.tag = binding

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence) =
                FilterResults().apply {
                    values = TypeModel.values()
                    count = TypeModel.values().size
                }

            override fun publishResults(constraint: CharSequence, results: FilterResults) = Unit
        }
    }
}
