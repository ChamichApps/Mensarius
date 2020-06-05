package am.chamich.app.account.features.modify.add

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.databinding.AccountFragmentAddAccountBinding
import am.chamich.app.account.extensions.textAsString
import am.chamich.app.account.extensions.viewModel
import am.chamich.app.account.features.modify.adapters.ColorAdapter
import am.chamich.app.account.features.modify.adapters.CurrencyAdapter
import am.chamich.app.account.features.modify.adapters.TypeAdapter
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.CurrencyModel
import am.chamich.app.account.models.DefaultAccountModel
import am.chamich.app.account.models.TypeModel
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView

internal class AddAccountFragment : CoreFragment<AccountFragmentAddAccountBinding>() {

    private lateinit var addAccountViewModel: AddAccountViewModel
    private var initialAccountModel = DefaultAccountModel()

    override var layoutId: Int = R.layout.account_fragment_add_account

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addAccountViewModel = viewModel(viewModelFactory) {
            // This model observes nothing
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        setHasOptionsMenu(true)
        binding.accountModel = initialAccountModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                addAccountViewModel.saveAccount(
                    AccountEntity(
                        name = binding.edittextAccountName.textAsString,
                        number = binding.edittextBankAccountNumber.textAsString,
                        value = binding.edittextInitialValue.textAsString.toLong(),
                        type = initialAccountModel.type,
                        color = initialAccountModel.color,
                        currency = initialAccountModel.currency
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onColorChanged(newColor: ColorModel?) {
        newColor?.let { color ->
            initialAccountModel = initialAccountModel.copy(color = color.id)
            binding.viewColor.accountColor = color
            binding.edittextAccountColor.dismissDropDown()
        }
    }

    private fun onTypeChanged(newType: TypeModel?) {
        newType?.let { type ->
            initialAccountModel = initialAccountModel.copy(type = type.id)
            binding.edittextAccountType.setText(type.stringResource)
            binding.edittextAccountType.dismissDropDown()
        }
    }

    private fun onCurrencyChanged(newCurrency: CurrencyModel?) {
        newCurrency?.let { currency ->
            initialAccountModel = initialAccountModel.copy(currency = currency.id)
            binding.edittextAccountCurrency.setText(currency.stringResource)
            binding.edittextAccountCurrency.dismissDropDown()
        }
    }

    private fun initializeAdapters() {
        (binding.edittextAccountType as? AutoCompleteTextView)?.setAdapter(
            TypeAdapter(requireContext()) { selectedType ->
                onTypeChanged(selectedType)
            }
        )
        (binding.edittextAccountColor as? AutoCompleteTextView)?.setAdapter(
            ColorAdapter(requireContext()) { selectedColor ->
                onColorChanged(selectedColor)
            }
        )
        (binding.edittextAccountCurrency as? AutoCompleteTextView)?.setAdapter(
            CurrencyAdapter(requireContext()) { selectedCurrency ->
                onCurrencyChanged(selectedCurrency)
            }
        )
    }
}