package com.example.binlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.binlist.databinding.ItemBinBinding
import com.example.binlist.domain.entity.BinItem

class BinListAdapter : ListAdapter<BinItem, BinInfoViewHolder>(BinInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinInfoViewHolder {
        val binding = ItemBinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinInfoViewHolder, position: Int) {
        val binInfo = getItem(position)
        with(holder.binding) {
            with(binInfo) {
                textViewBin.text = bin
                textViewCountryEmoji.text = emoji
                textViewCountryName.text = country
                textViewBankName.text = bank
            }
        }
    }
}