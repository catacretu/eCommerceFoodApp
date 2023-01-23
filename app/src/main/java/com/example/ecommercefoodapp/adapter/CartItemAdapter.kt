package com.example.ecommercefoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.listener.ItemClickListener

class CartItemAdapter(
    private val itemsList: List<FoodItemEntity>,
    private val context: Context,
    private val clickListener: ItemClickListener,
    private val addClickListener: ItemClickListener,
    private val decreaseClickListener: ItemClickListener

) :
    RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart_view, parent, false)
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
        private val quantity: TextView = itemView.findViewById(R.id.quantity)
        private val addButton: Button = itemView.findViewById(R.id.add_button)
        private val decreaseButton: Button = itemView.findViewById(R.id.decrease_button)

        fun bind(item: FoodItemEntity) {
            title.text = item.title
            price.text = item.price
            quantity.text = item.quantity.toString()
            Glide.with(context).load(item.imageUrl).into(image)
            image.setOnClickListener { clickListener.onClick(item) }
            addButton.setOnClickListener { addClickListener.onClick(item) }
            decreaseButton.setOnClickListener { decreaseClickListener.onClick(item) }
        }
    }
}