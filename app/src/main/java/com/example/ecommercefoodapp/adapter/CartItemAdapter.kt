package com.example.ecommercefoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
    private val clickListener: ItemClickListener

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
        private val quantity: EditText = itemView.findViewById(R.id.quantity)

        fun bind(item: FoodItemEntity) {
            title.text = item.title
            price.text = item.price
            quantity.setText(item.quantity.toString())
            Glide.with(context).load(item.imageUrl).into(image)
            image.setOnClickListener { clickListener.onClick(item) }
        }
    }

}