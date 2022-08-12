package com.bendingbytes.shoes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bendingbytes.shoes.R
import com.bendingbytes.shoes.domain.Shoe
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_shoe.view.*

class ShoeAdapter : ListAdapter<Shoe, ShoeAdapter.ShoeViewHolder>(ShoeItemDiffCallback()) {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(shoe: Shoe)
    }

    inner class ShoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shoe, parent, false)
        return ShoeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe: Shoe = getItem(position)
        val context: Context = holder.itemView.context
        holder.itemView.textViewTitle.text = context.getString(R.string.name, shoe.name)
        holder.itemView.textViewPrice.text = context.getString(R.string.price, shoe.price)
        Glide.with(holder.itemView).load(shoe.image).into(holder.itemView.imageViewShoe)
        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(getItem(holder.bindingAdapterPosition)) }
    }
}

class ShoeItemDiffCallback : DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldShoe: Shoe, newShoe: Shoe): Boolean = oldShoe.id == newShoe.id
    override fun areContentsTheSame(oldShoe: Shoe, newShoe: Shoe): Boolean = oldShoe == newShoe
}