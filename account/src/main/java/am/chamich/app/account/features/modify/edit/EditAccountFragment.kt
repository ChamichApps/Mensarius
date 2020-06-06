package am.chamich.app.account.features.modify.edit

import am.chamich.app.account.R
import am.chamich.app.account.core.CoreFragment
import am.chamich.app.account.database.entity.AccountEntity
import am.chamich.app.account.databinding.AccountFragmentEditAccountBinding
import am.chamich.app.account.extensions.observe
import am.chamich.app.account.extensions.textAsString
import am.chamich.app.account.extensions.viewModel
import am.chamich.app.account.features.modify.adapters.ColorAdapter
import am.chamich.app.account.features.modify.adapters.TypeAdapter
import am.chamich.app.account.models.AccountModel
import am.chamich.app.account.models.ColorModel
import am.chamich.app.account.models.DefaultAccountModel
import am.chamich.app.account.models.TypeModel
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.navArgs

internal class EditAccountFragment : CoreFragment<AccountFragmentEditAccountBinding>() {

    private lateinit var editAccountViewModel: EditAccountViewModel
    private lateinit var initialAccountEntity: AccountEntity
    private lateinit var currentColor: ColorModel
    private lateinit var currentType: TypeModel
    private val args: EditAccountFragmentArgs by navArgs()

    override var layoutId: Int = R.layout.account_fragment_edit_account

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editAccountViewModel = viewModel(viewModelFactory) {
            observe(loadedAccount, ::handleLoadedAccount)
            observe(deletedAccount, ::handleDeletedAccount)
            observe(updatedAccount, ::handleUpdatedAccount)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        setHasOptionsMenu(true)
        binding.accountModel = DefaultAccountModel()
        showProgress()
        editAccountViewModel.loadAccount(args.accountId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                showProgress()
                editAccountViewModel.deleteAccount(args.accountId)
            }
            R.id.action_save -> {
                showProgress()
                editAccountViewModel.updateAccount(
                    initialAccountEntity.copy(
                        name = binding.edittextAccountName.textAsString,
                        number = binding.edittextBankAccountNumber.textAsString,
                        color = currentColor.id,
                        type = currentType.id
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onTypeChanged(newType: TypeModel?) {
        newType?.let {
            binding.edittextAccountType.setText(it.stringResource)
            binding.edittextAccountType.dismissDropDown()
            currentType = it
        }
    }

    private fun onColorChanged(newColor: ColorModel?) {
        newColor?.let {
            binding.viewColor.accountColor = it
            binding.edittextAccountColor.dismissDropDown()
            currentColor = it
        }
    }

    private fun handleLoadedAccount(accountEntity: AccountEntity?) {
        hideProgress()
        accountEntity?.let {
            initialAccountEntity = it
            currentColor = ColorModel.from(it.color)
            currentType = TypeModel.from(it.type)
            binding.accountModel = AccountModel(
                it.id, it.name, it.number, it.value, it.type, it.currency, it.color
            )
        }
    }

    private fun handleDeletedAccount(ignore: Unit?) {
        hideProgress()
        navigator.navigateBack(this)
    }

    private fun handleUpdatedAccount(ignore: Unit?) {
        hideProgress()
        navigator.navigateBack(this)
    }

    private fun initializeAdapters() {
        (binding.edittextAccountType as? AutoCompleteTextView)?.setAdapter(
            TypeAdapter(requireContext()) { newType ->
                onTypeChanged(newType)
            }
        )
        (binding.edittextAccountColor as? AutoCompleteTextView)?.setAdapter(
            ColorAdapter(requireContext()) { newColor ->
                onColorChanged(newColor)
            }
        )
    }
}