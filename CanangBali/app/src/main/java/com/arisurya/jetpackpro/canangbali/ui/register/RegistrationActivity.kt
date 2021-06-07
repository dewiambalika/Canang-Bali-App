package com.arisurya.jetpackpro.canangbali.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityRegistrationBinding
import com.arisurya.jetpackpro.canangbali.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {
    lateinit var  auth : FirebaseAuth
    var databaseReference : DatabaseReference?=null
    var database : FirebaseDatabase?=null
    private lateinit var binding : ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference?.child("profile")
        register()
    }

    private fun register(){
//        val gender = resources.getStringArray(R.array.gender)
//        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, gender)
//        binding.genderInput.setAdapter(arrayAdapter)

        binding.registerButton.setOnClickListener {
            if(TextUtils.isEmpty(binding.firstnameInput.text.toString())) {
                binding.firstnameInput.setError("Please enter first name ")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(binding.lastnameInput.text.toString())) {
                binding.lastnameInput.setError("Please enter last name ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(binding.ageInput.text.toString())) {
                binding.ageInput.setError("Please enter age ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(binding.locationInput.text.toString())) {
                binding.locationInput.setError("Please enter location ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(binding.tlpInput.text.toString())) {
                binding.tlpInput.setError("Please enter telephone ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(binding.emailInput.text.toString())) {
                binding.emailInput.setError("Please enter email ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(binding.passwordInput.text.toString()) || binding.passwordInput.text.length < 6) {
                binding.passwordInput.setError("Must not empty and more then 6 character")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(binding.emailInput.text.toString(), binding.passwordInput.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("firstname")?.setValue(binding.firstnameInput.text.toString())
                        currentUserDb?.child("lastname")?.setValue(binding.lastnameInput.text.toString())
                        currentUserDb?.child("age")?.setValue(binding.ageInput.text.toString())
                        currentUserDb?.child("address")?.setValue(binding.locationInput.text.toString())
                        currentUserDb?.child("tlp")?.setValue(binding.tlpInput.text.toString())
                        currentUserDb?.child("email")?.setValue(binding.emailInput.text.toString())

                        Toast.makeText(this@RegistrationActivity, "Registration Success. ", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Registration Failed, Try Again", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        binding.loginText.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}