package com.arisurya.jetpackpro.canangbali.ui.information.canang

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.databinding.ItemCanangBinding
import com.arisurya.jetpackpro.canangbali.ui.information.canang.detail.DetailCanangActivity
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraActivity
import com.bumptech.glide.Glide

class CanangAdapter : RecyclerView.Adapter<CanangAdapter.CanangViewHolder>() {

    private var listCanang = ArrayList<CanangEntity>()

    fun setCanang(canang: List<CanangEntity>?) {
        if (canang == null) return
        this.listCanang.clear()
        this.listCanang.addAll(canang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanangViewHolder {
        val itemCanangBinding =
            ItemCanangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CanangViewHolder(itemCanangBinding)

    }

    override fun onBindViewHolder(holder: CanangViewHolder, position: Int) {
        val canang = listCanang[position]
        holder.bind(canang)

    }

    override fun getItemCount(): Int = listCanang.size


    class CanangViewHolder(private val binding: ItemCanangBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(canang: CanangEntity) {
            with(binding) {
                tvTitleCanang.text = canang.title
                tvDesc.text = canang.function
                Glide.with(itemView.context)
                    .load(canang.imgPath)
                    .into(imgCanang)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCanangActivity::class.java)
                    intent.putExtra(DetailCanangActivity.EXTRA_CANANG, canang.canangId.toString())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}