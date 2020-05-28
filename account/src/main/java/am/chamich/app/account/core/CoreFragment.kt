package am.chamich.app.account.core

import am.chamich.app.account.di.AccountComponent
import am.chamich.app.account.features.AccountsActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

internal abstract class CoreFragment<T : ViewDataBinding> : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract var layoutId: Int

    protected lateinit var binding: T

    protected val accountComponent: AccountComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity() as? AccountsActivity)?.accountComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }
}