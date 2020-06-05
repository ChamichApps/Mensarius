package am.chamich.app.account.features.modify.adapters.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

internal abstract class CoreAdapter<T, B : ViewDataBinding>(
    context: Context,
    private val resource: Int,
    private val items: Array<T>
) : ArrayAdapter<T>(context, resource, items) {

    protected abstract var binding: B

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return if (convertView == null) {
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), resource, parent, false
            )
            binding.root
        } else {
            binding = convertView.tag as B
            convertView
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence) =
                FilterResults().apply {
                    values = items
                    count = items.size
                }

            override fun publishResults(constraint: CharSequence, results: FilterResults) = Unit
        }
    }
}