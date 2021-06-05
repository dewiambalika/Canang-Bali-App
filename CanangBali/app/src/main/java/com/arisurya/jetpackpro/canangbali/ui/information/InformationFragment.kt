package com.arisurya.jetpackpro.canangbali.ui.information

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.FragmentInformationBinding
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangActivity
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.philosophy.PhilosophyActivity
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopActivity
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraActivity
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraAdapter
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status

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
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = "Information"
        (activity as AppCompatActivity).supportActionBar?.elevation = 0F

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[InformationViewModel::class.java]


            val upakaraAdapter = UpakaraAdapter()
            showProgressBar(true)
            viewModel.getUpakara().observe(viewLifecycleOwner, {upakara->
                if (upakara!=null){
                    when(upakara.status){
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS ->{
                            showProgressBar(false)
                            upakaraAdapter.setUpakara(upakara.data)
                            upakaraAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentInformationBinding.rvUpakara) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upakaraAdapter
            }


            val canangAdapter = CanangAdapter()
            viewModel.getCanang().observe(viewLifecycleOwner, {canang->
                when(canang.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS->{
                        showProgressBar(false)
                        canangAdapter.setCanang(canang.data)
                        canangAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            })
            with(fragmentInformationBinding.rvCanang) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = canangAdapter
            }


            val shopAdapter = ShopAdapter()
            viewModel.getShop().observe(viewLifecycleOwner, {shop->
                when(shop.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS ->{
                        showProgressBar(false)
                        shopAdapter.setShop(shop.data)
                        shopAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        showProgressBar(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            })
            with(fragmentInformationBinding.rvShop) {
                this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = shopAdapter
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

    private fun showProgressBar(state : Boolean){
        if(state){
            fragmentInformationBinding.progressBar.visibility = View.VISIBLE
        }else{
            fragmentInformationBinding.progressBar.visibility = View.GONE
        }
    }

}