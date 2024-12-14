package com.rarmash.prac5

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private var carts: List<Cart>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carts, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = carts[position]
        holder.bind(cart)
    }

    override fun getItemCount(): Int {
        return carts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCarts(newCarts: List<Cart>) {
        this.carts = newCarts
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cartTotalDiscounted: TextView = itemView.findViewById(R.id.cartTotalDiscounted)
        private val cartUserId: TextView = itemView.findViewById(R.id.cartUserId)

        fun bind(cart: Cart) {
            cartTotalDiscounted.text = cart.discountedTotal.toString()
            cartUserId.text = cart.userId.toString()
        }
    }
}
