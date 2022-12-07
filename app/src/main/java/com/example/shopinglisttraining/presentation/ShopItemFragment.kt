package com.example.shopinglisttraining.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglisttraining.R
import com.example.shopinglisttraining.databinding.FragmentShopItemBinding
import com.example.shopinglisttraining.domain.ShopItem

class ShopItemFragment : Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener
    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

//    private lateinit var titleName: TextInputLayout
//    private lateinit var titleCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var buttonSave: Button

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ShopItemFragment", "onCreate")
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
//        initViews(view)
        addTextChangeListeners()
        lunchMode()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.accessToClose.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
//        viewModel.errorInputCount.observe(viewLifecycleOwner) {
//            val message = if (it) {
//                getString(R.string.error_msg_input)
//            } else {
//                null
//            }
//            binding.etTitleCount.error = message
//        }
//        viewModel.errorInputName.observe(viewLifecycleOwner) {
//            val message = if (it) {
//                getString(R.string.error_msg_input)
//            } else {
//                null
//            }
//            binding.etTitleName.error = message
//        }

    }

    private fun addTextChangeListeners() {
        binding.etTiName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.etTiCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun lunchMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getItemByIdUseCase(shopItemId)
//        viewModel.shopItemById.observe(viewLifecycleOwner) {
//            val name = it.name
//            val count = it.count
//            binding.etTiName.setText(name)
//            binding.etTiCount.setText(count.toString())
//        }
        binding.buttonItem.setOnClickListener {
            viewModel.editItemShopItem(
                binding.etTiName.text?.toString(),
                binding.etTiCount.text?.toString()
            )

        }
    }

    private fun launchAddMode() {
        binding.buttonItem.setOnClickListener {
            viewModel.addItemUseCase(
                binding.etTiName.text.toString(), binding.etTiCount.text
                    .toString()
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(ID_ITEM)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(ID_ITEM, ShopItem.UNDEFINED_ID)
        }

    }

//    private fun initViews(view: View) {
//        titleName = view.findViewById(R.id.et_title_name)
//        titleCount = view.findViewById(R.id.et_title_count)
//        etName = view.findViewById(R.id.et_ti_name)
//        etCount = view.findViewById(R.id.et_ti_count)
//        buttonSave = view.findViewById(R.id.button_item)
//    }


    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val ID_ITEM = "id_item"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }

            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ID_ITEM, shopItemId)
                }
            }
        }

    }
}