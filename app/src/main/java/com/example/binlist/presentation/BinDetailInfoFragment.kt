package com.example.binlist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.binlist.R
import com.example.binlist.databinding.FragmentBinDetailInfoBinding
import javax.inject.Inject

class BinDetailInfoFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as BinApp).component
    }

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[BinViewModel::class.java]
    }

    private var _binding: FragmentBinDetailInfoBinding? = null
    private val binding: FragmentBinDetailInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentBinDetailInfoBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBinDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bin = getBin()
        viewModel.getBinInfo(bin)
        initViews(bin)
    }

    private fun initViews(bin: String) {
        viewModel.binInfo.observe(viewLifecycleOwner) {
            with(binding) {
                textViewBin.text = bin
                textViewScheme.text = it.scheme ?: EMPTY_SYMBOL
                textViewBrand.text = it.brand ?: EMPTY_SYMBOL
                it.number?.length.let {
                    textViewCardNumberLength.text = it?.toString() ?: EMPTY_SYMBOL
                }
                when(it.number?.luhn) {
                    true -> textViewCardNumberLuhnYes.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    false -> textViewCardNumberLuhnNo.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> null
                }
                when (it.type) {
                    "debit" -> textViewTypeDebit.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    "credit" -> textViewTypeCredit.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> null
                }
                when(it.prepaid) {
                    true -> textViewPrepaidYes.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    false -> textViewPrepaidNo.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> null
                }
                textViewCountry.text = it.country?.emoji ?: EMPTY_SYMBOL
                textViewCountryName.text = it.country?.name ?: EMPTY_SYMBOL
                textViewBankName.text = it.bank?.name ?: EMPTY_SYMBOL
                textViewBankSite.text = it.bank?.url ?: EMPTY_SYMBOL
                textViewBankPhone.text = it.bank?.phone ?: EMPTY_SYMBOL
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getBin(): String {
        return requireArguments().getString(EXTRA_KEY, EMPTY_SYMBOL)
    }

    companion object {

        private const val EXTRA_KEY = "BIN"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(bin: String): Fragment {
            return BinDetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_KEY, bin)
                }
            }
        }
    }
}
