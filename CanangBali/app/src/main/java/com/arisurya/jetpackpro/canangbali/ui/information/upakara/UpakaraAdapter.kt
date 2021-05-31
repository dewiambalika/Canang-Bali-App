package com.arisurya.jetpackpro.canangbali.ui.information.upakara

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.databinding.ItemUpakaraBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraActivity
import com.bumptech.glide.Glide

class UpakaraAdapter : RecyclerView.Adapter<UpakaraAdapter.UpakaraViewHolder>() {

    private var listUpakara = ArrayList<UpakaraEntity>()

    fun setUpakara(upakara: List<UpakaraEntity>?) {
        if (upakara == null) return
        this.listUpakara.clear()
        this.listUpakara.addAll(upakara)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpakaraViewHolder {
        val itemUpakaraBinding =
            ItemUpakaraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpakaraViewHolder(itemUpakaraBinding)

    }

    override fun onBindViewHolder(holder: UpakaraViewHolder, position: Int) {
        val upakara = listUpakara[position]
        holder.bind(upakara)

    }

    override fun getItemCount(): Int = listUpakara.size


    class UpakaraViewHolder(private val binding: ItemUpakaraBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upakara: UpakaraEntity) {
            with(binding) {
                tvTitleUpakara.text = upakara.title
                tvDesc.text = upakara.desc
                Glide.with(itemView.context)
                    .load(upakara.imgPath)
                    .into(imgUpakara)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailUpakaraActivity::class.java)
                    intent.putExtra(DetailUpakaraActivity.EXTRA_UPAKARA, upakara.upakaraId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}