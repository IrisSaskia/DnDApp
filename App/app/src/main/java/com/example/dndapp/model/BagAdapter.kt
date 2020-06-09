package com.example.dndapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import kotlinx.android.synthetic.main.item_item.view.*

class BagAdapter(private val bagItems: List<BagItem>, private val onClick: (BagItem) -> Unit) : RecyclerView.Adapter<BagAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }
    /* Returns the size of the list*/

    override fun getItemCount(): Int {
        return bagItems.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bagItems[position])
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(bagItem: BagItem){
            itemView.tvAmount.text = bagItem.amount.toString()
            itemView.tvName.text = bagItem.name
            itemView.tvWeight.text = bagItem.weight.toString()
        }
        init {
            itemView.setOnClickListener { onClick(bagItems[adapterPosition]) }
        }
    }
}
