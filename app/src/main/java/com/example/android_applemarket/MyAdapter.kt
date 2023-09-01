package com.example.android_applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_applemarket.databinding.ActivityMainBinding
import com.example.android_applemarket.databinding.ItemBinding
import java.text.DecimalFormat
import java.text.FieldPosition

class MyAdapter(val mItems: MutableList<Product>) : RecyclerView.Adapter<MyAdapter.Holder>() {
    interface ItemClick {
        fun onClick(view : View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemLongClick: ItemLongClick? = null
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }
        holder.productImage.setImageResource(mItems[position].productImage)
        holder.title.text = mItems[position].productTitle
        holder.address.text = mItems[position].address
        holder.price.text = getDecimalFormat(mItems[position].price)
        holder.chatCount.text = mItems[position].chattingCount.toString()
        holder.likeCount.text = mItems[position].likeCount.toString()

        if(mItems[position].isLike) {
            holder.isLike.setImageResource(R.drawable.ic_detail_heart)
        }
        else {
            holder.isLike.setImageResource(R.drawable.ic_main_like)
        }
    }
    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val productImage = binding.itemImageImageButton
        val title = binding.itemTitleTextView
        val address = binding.itemAddressTextView
        val price = binding.itemPriceTextView
        val chatCount = binding.itemChatCountTextView
        val likeCount = binding.itemLikeCountTextView
        val isLike = binding.itemLikeImageView
    }
    private fun getDecimalFormat(number: Int): String {
        val deciamlFormat = DecimalFormat("#,###")
        return deciamlFormat.format(number) + "원"
    }
}