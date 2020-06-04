package am.chamich.app.account.features.modify.edit

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.databinding.AccountFragmentEditAccountBinding
import am.chamich.app.account.extensions.observe
import am.chamich.app.account.extensions.viewModel
import am.chamich.app.account.features.modify.adapters.AccountColorAdapter
import am.chamich.app.account.features.modify.adapters.AccountTypeAdapter
import am.chamich.app.account.models.AccountModel
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.DefaultAccountModel
import am.chamich.app.account.models.TypeModel
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.navArgs

internal class EditAccountFragment : CoreFragment<AccountFragmentEditAccountBinding>() {

    private lateinit var editAccountViewModel: EditAccountViewModel
    val args: EditAccountFragmentArgs by navArgs()

    override var layoutId: Int = R.layout.account_fragment_edit_account

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editAccountViewModel = viewModel(viewModelFactory) {
            observe(loadedAccount, ::handleLoadedAccount)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        binding.accountModel = DefaultAccountModel()
        editAccountViewModel.loadAccount(args.accountId)
    }

    private fun onAccountTypeClicked(typeModel: TypeModel) {
        binding.edittextAccountType.setText(typeModel.stringResource)
        binding.edittextAccountType.dismissDropDown()
    }

    private fun onAccountColorClicked(colorModel: ColorModel) {
        binding.viewColor.accountColor = colorModel
        binding.edittextAccountColor.dismissDropDown()
    }

    private fun handleLoadedAccount(account: AccountEntity?) {
        account?.let {
            binding.accountModel = AccountModel(
                it.id, it.name, it.number, it.value, it.type, it.currency, it.color
            )
        }
    }

    private fun initializeAdapters() {
        (binding.edittextAccountType as? AutoCompleteTextView)?.setAdapter(
            AccountTypeAdapter(requireContext()) { selectedType ->
                onAccountTypeClicked(selectedType)
            }
        )
        (binding.edittextAccountColor as? AutoCompleteTextView)?.setAdapter(
            AccountColorAdapter(requireContext()) { selectedColor ->
                onAccountColorClicked(selectedColor)
            }
        )
    }
}