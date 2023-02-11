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
                textViewScheme.text = it.scheme ?: "-"
                textViewBrand.text = it.brand ?: "-"
                textViewCardNumberLength.text = it.number?.length.toString()
                when(it.number?.luhn) {
                    true -> textViewCardNumberLuhnYes.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    false -> textViewCardNumberLuhnNo.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> throw RuntimeException("LUHN is null")
                }
                when (it.type) {
                    "debit" -> textViewTypeDebit.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    "credit" -> textViewTypeCredit.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> throw RuntimeException("TYPE is null")
                }
                when(it.prepaid) {
                    true -> textViewPrepaidYes.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    false -> textViewPrepaidNo.setTextAppearance(R.style.DetailInfoBooleanStyle)
                    else -> throw RuntimeException("PREPAID is null")
                }
                textViewCountry.text = it.country?.emoji ?: "-"
                textViewCountryName.text = it.country?.name ?: "-"
                textViewBankName.text = it.bank?.name ?: "-"
                textViewBankSite.text = it.bank?.url ?: "-"
                textViewBankPhone.text = it.bank?.phone ?: "-"
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
