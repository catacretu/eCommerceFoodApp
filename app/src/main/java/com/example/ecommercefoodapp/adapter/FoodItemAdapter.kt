package com.example.ecommercefoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.listener.ItemClickListener

class FoodItemAdapter(
    private val itemsList: List<FoodItemEntity>,
    private val context: Context,
    private val clickListener: ItemClickListener
) :
    RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(item: FoodItemEntity) {
            title.text = item.title
            price.text = item.price
            Glide.with(context).load(item.imageUrl).into(image)
            itemView.setOnClickListener { clickListener.onClick(item)}
        }
    }
}