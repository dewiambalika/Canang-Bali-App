package com.arisurya.jetpackpro.canangbali.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.arisurya.jetpackpro.canangbali.MainActivity
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityLoginBinding
import com.arisurya.jetpackpro.canangbali.ui.register.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        if (currentuser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        showProgressBar(false)
        login()
    }

    private fun login() {

        binding.loginButton.setOnClickListener {
            showProgressBar(true)
            if (TextUtils.isEmpty(binding.usernameInput.text.toString())) {
                binding.usernameInput.error = "Please enter username"
                showProgressBar(false)
                return@setOnClickListener
            } else if (TextUtils.isEmpty(binding.passwordInput.text.toString())) {
                binding.usernameInput.error = "Please enter password"
                showProgressBar(false)
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        showProgressBar(false)
                        finish()
                    } else {
                        showProgressBar(false)
                        Toast.makeText(
                            this@LoginActivity,
                            "Login gagal, coba lagi! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }

        binding.registerText.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
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