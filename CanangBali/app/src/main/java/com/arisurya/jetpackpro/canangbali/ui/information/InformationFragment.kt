package com.arisurya.jetpackpro.canangbali.ui.information

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arisurya.jetpackpro.canangbali.databinding.FragmentInformationBinding
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangActivity
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.philosophy.PhilosophyActivity
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopActivity
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraActivity
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraAdapter

class InformationFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentInformationBinding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentInformationBinding =
            FragmentInformationBinding.inflate(layoutInflater, container, false)
        return fragmentInformationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[InformationViewModel::class.java]
            val upakara = viewModel.getUpakara()

            val upakaraAdapter = UpakaraAdapter()
            upakaraAdapter.setUpakara(upakara)
            with(fragmentInformationBinding.rvUpakara) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upakaraAdapter
            }

            val canang = viewModel.getCanang()
            val canangAdapter = CanangAdapter()
            canangAdapter.setCanang(canang)
            with(fragmentInformationBinding.rvCanang) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = canangAdapter
            }

            val shop = viewModel.getShop()
            val shopAdapter = ShopAdapter()
            shopAdapter.setShop(shop)
            with(fragmentInformationBinding.rvShop) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = shopAdapter
            }

            fragmentInformationBinding.apply {
                tvSeeAllUpakara.setOnClickListener(this@InformationFragment)
                tvSeeAllCanang.setOnClickListener(this@InformationFragment)
                tvSeeAllShop.setOnClickListener(this@InformationFragment)
                btnDetailPhi.setOnClickListener(this@InformationFragment)
            }

        }
    }

    override fun onClick(v: View?) {
       when(v){
           fragmentInformationBinding.tvSeeAllUpakara -> {
               startActivity(Intent(Intent(activity, UpakaraActivity::class.java)))
           }
           fragmentInformationBinding.tvSeeAllCanang -> {
               startActivity(Intent(Intent(activity, CanangActivity::class.java)))
           }
           fragmentInformationBinding.tvSeeAllShop -> {
               startActivity(Intent(Intent(activity, ShopActivity::class.java)))
           }
           fragmentInformationBinding.btnDetailPhi->{
               startActivity(Intent(Intent(activity, PhilosophyActivity::class.java)))
           }

       }
    }

}