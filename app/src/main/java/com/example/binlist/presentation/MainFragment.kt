package com.example.binlist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.binlist.R
import com.example.binlist.databinding.FragmentMainBinding
import com.example.binlist.presentation.adapter.BinListAdapter
import javax.inject.Inject

class MainFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as BinApp).component
    }

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[BinViewModel::class.java]
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private lateinit var binListAdapter: BinListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binListAdapter = BinListAdapter()
        binding.recyclerView.adapter = binListAdapter

        binding.buttonTextInput.setOnClickListener {
            val bin = getBin()
            startBinDetailInfoFragment(bin)
        }

        viewModel.binItem.observe(viewLifecycleOwner) {
            binListAdapter.submitList(it)
        }

        setupSwipeListener(binding.recyclerView)
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = binListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBinItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun startBinDetailInfoFragment(bin: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, BinDetailInfoFragment.newInstance(bin))
            .commit()
    }

    private fun getBin(): String {
        return binding.textInputEditText.text.toString()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
