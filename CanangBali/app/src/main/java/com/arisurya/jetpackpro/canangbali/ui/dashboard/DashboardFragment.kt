package com.arisurya.jetpackpro.canangbali.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.databinding.FragmentDashboardBinding
import com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark.BookmarkCanangActivity
import com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark.BookmarkShopActivity
import com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark.BookmarkUpakaraActivity
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory

class DashboardFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentDashboardBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentDashboardBinding =
            FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return fragmentDashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        viewModel.getCountBookmarkCanang().observe(viewLifecycleOwner, { number ->
            fragmentDashboardBinding.tvNumberCanang.text = number.toString()
        })
        viewModel.getCountBookmarkUpakara().observe(viewLifecycleOwner, { number ->
            fragmentDashboardBinding.tvNumberUpakara.text = number.toString()
        })
        viewModel.getCountBookmarkShop().observe(viewLifecycleOwner, { number ->
            fragmentDashboardBinding.tvNumberShop.text = number.toString()
        })

        fragmentDashboardBinding.apply {
            tvSeeCanang.setOnClickListener(this@DashboardFragment)
            tvSeeUpakara.setOnClickListener(this@DashboardFragment)
            tvSeeShop.setOnClickListener(this@DashboardFragment)
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            fragmentDashboardBinding.tvSeeCanang -> {
                startActivity(Intent(Intent(activity, BookmarkCanangActivity::class.java)))
            }
            fragmentDashboardBinding.tvSeeUpakara -> {
                startActivity(Intent(Intent(activity, BookmarkUpakaraActivity::class.java)))
            }
            fragmentDashboardBinding.tvSeeShop -> {
                startActivity(Intent(Intent(activity, BookmarkShopActivity::class.java)))
            }

        }
    }

}