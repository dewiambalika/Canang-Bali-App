package com.arisurya.jetpackpro.canangbali.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.FragmentAccountBinding
import com.arisurya.jetpackpro.canangbali.databinding.FragmentInformationBinding
import com.arisurya.jetpackpro.canangbali.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class AccountFragment : Fragment() {

    private lateinit var binding : FragmentAccountBinding
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = "Account"
        (activity as AppCompatActivity).supportActionBar?.elevation = 0F
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressBar(true)
        if(activity!=null){

            auth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            databaseReference = database?.reference!!.child("profile")

            loadProfile()
        }

    }

    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var firstName = snapshot.child("firstname").value.toString()
                var lastName = snapshot.child("lastname").value.toString()
                binding.tvAccountName.text = StringBuilder().append(firstName).append(" ").append(lastName)
                binding.tvAccountAge.text = snapshot.child("age").value.toString()
                binding.tvAccountTlp.text = snapshot.child("tlp").value.toString()
                binding.tvAccountAddress.text = snapshot.child("address").value.toString()
                binding.tvAccountEmail.text = snapshot.child("email").value.toString()
                showProgressBar(false)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        binding.btnEditAccount.setOnClickListener {
            auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

}