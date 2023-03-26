package com.example.avitotestassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotestassignment.databinding.ItemBinding

class ItemListAdapter: ListAdapter<Item, ItemListAdapter.ItemHolder>(ItemDiffCallBack()) {

    class ItemHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemBinding.bind(view)
    }

    class ItemDiffCallBack: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.value == newItem.value
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
    }

    var onButtonDelClickListener: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false)
        return ItemHolder(view)
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvNumber.text = item.value.toString()
        holder.binding.bDel.setOnClickListener {
            onButtonDelClickListener?.invoke(item)
        }
    }
}