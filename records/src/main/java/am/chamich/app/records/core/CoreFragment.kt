package am.chamich.app.records.core

import am.chamich.app.records.di.RecordsComponent
import am.chamich.app.records.features.RecordsActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

internal abstract class CoreFragment<T : ViewDataBinding> : Fragment() {

    protected abstract var layoutId: Int

    protected lateinit var binding: T

    protected val recordsComponent: RecordsComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity() as? RecordsActivity)?.recordsComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }
}