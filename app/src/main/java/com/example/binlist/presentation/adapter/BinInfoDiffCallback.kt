package com.example.binlist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.binlist.domain.entity.BinItem

object BinInfoDiffCallback: DiffUtil.ItemCallback<BinItem>() {

    override fun areItemsTheSame(oldItem: BinItem, newItem: BinItem): Boolean {
        return oldItem.bin == newItem.bin
    }

    override fun areContentsTheSame(oldItem: BinItem, newItem: BinItem): Boolean {
        return oldItem == newItem
    }
}