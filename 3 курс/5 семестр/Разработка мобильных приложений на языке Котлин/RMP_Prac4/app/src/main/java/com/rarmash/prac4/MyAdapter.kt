package com.rarmash.prac4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: List<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView =
            view.findViewById(R.id.itemTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent, false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val item = data[position]
        holder.textView.text = item
    }

    override fun getItemCount() = data.size
}