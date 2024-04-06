package com.application.dripjoycoffee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private val itemList: List<ProductDataClass>,
    private val onItemClick: (ProductDataClass) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.special_img_second)
        private val skinNameTextView: TextView = itemView.findViewById(R.id.SkinName)
        private val priceTextView: TextView = itemView.findViewById(R.id.price)
        private val documentTextView: TextView = itemView.findViewById(R.id.document_second)

        init {
            itemView.setOnClickListener {
                onItemClick(itemList[adapterPosition])
            }
        }

        fun bind(item: ProductDataClass) {
            skinNameTextView.text = item.name
            priceTextView.text = "$" +
                    "" +
                    "${item.price}"

            val imageUrl = "https://srv1322-files.hstgr.io/304d909a8bcd9aa7/files/laravel/storage/tmp/uploads/${item.galleries}"
            Glide.with(itemView.context)
                .load(imageUrl)
                .error(R.drawable.baseline_error_24)
                .into(imageView)

            itemView.setOnClickListener {
                onItemClick(item)
            }
            imageView.setOnClickListener {
                onItemClick(item)
            }
            skinNameTextView.setOnClickListener {
                onItemClick(item)
            }
            priceTextView.setOnClickListener {
                onItemClick(item)
            }
            documentTextView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}