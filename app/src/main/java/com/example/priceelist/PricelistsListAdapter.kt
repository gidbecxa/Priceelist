package com.example.priceelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.priceelist.data.Pricelist
import com.example.priceelist.data.getFormattedPrice
import com.example.priceelist.databinding.SavedListsItemTwoBinding

class PricelistsListAdapter(private val onItemClicked: (Pricelist) -> Unit, private val onItemLongClicked: (Pricelist) -> Unit):
    ListAdapter<Pricelist, PricelistsListAdapter.PricelistViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PricelistViewHolder {
        return PricelistViewHolder (
            SavedListsItemTwoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: PricelistViewHolder,
        position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClicked(current)
            true
        }
        holder.bind(current)
    }

    class PricelistViewHolder(private var binding: SavedListsItemTwoBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind (item: Pricelist) {
                binding.listTitle.text = item.dbListTitle
                binding.listTotal.text = item.getFormattedPrice()
                binding.listDate.text = item.date
                binding.listType.text = item.listType
            }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Pricelist>() {
            override fun areContentsTheSame(oldItem: Pricelist, newItem: Pricelist): Boolean {
                return oldItem.dbListTitle == newItem.dbListTitle
            }

            override fun areItemsTheSame(oldItem: Pricelist, newItem: Pricelist): Boolean {
                return oldItem.dbListTitle == newItem.dbListTitle
            }
        }
    }

}