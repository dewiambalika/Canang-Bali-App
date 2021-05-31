package com.arisurya.jetpackpro.canangbali.ui.information.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.databinding.ItemShopBinding
import com.bumptech.glide.Glide

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    private var listShop = ArrayList<ShopEntity>()

    fun setShop(shop: List<ShopEntity>?) {
        if (shop == null) return
        this.listShop.clear()
        this.listShop.addAll(shop)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val itemShopBinding =
            ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(itemShopBinding)

    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = listShop[position]
        holder.bind(shop)

    }

    override fun getItemCount(): Int = listShop.size


    class ShopViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: ShopEntity) {
            with(binding) {
                tvTitleShop.text = shop.name
                tvLocation.text = shop.location
                Glide.with(itemView.context)
                    .load(shop.imgPath)
                    .into(imgShop)

                itemView.setOnClickListener {
                    return@setOnClickListener
                }
            }
        }
    }
}