package com.arisurya.jetpackpro.canangbali.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.arisurya.jetpackpro.canangbali.R


class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = "Account"
        (activity as AppCompatActivity).supportActionBar?.elevation = 0F
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
}