package com.example.android2021.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.android2021.databinding.ActivityLoginBinding
import com.example.android2021.R
import com.example.android2021.ui.signup.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity() : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        // Layout views
        val username = binding.username
        val password = binding.password
        val login = binding.login
        val signup = binding.signup

        var intentUserName = intent.getStringExtra(R.string.extra_username.toString());
        var intentPassword = intent.getStringExtra(R.string.extra_password.toString());


        // Set username passed from Signup Screen
        if (!intentUserName.isNullOrBlank()) {
            username.setText(intentUserName);
        }
        // Set password passed from Signup Screen
        if (!intentPassword.isNullOrBlank()) {
            password.setText(intentPassword);
        }

        // Login button clicked
        login.setOnClickListener {
            this@LoginActivity.signIn(username.text.toString(), password.text.toString());
        }

        // Signup button clicked
        signup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                var message = "Failed"
                if (task.isSuccessful) {
                    message = "Logged in as ${auth.currentUser?.email}"
                }
                Toast.makeText(
                    baseContext
                    , message
                    , Toast.LENGTH_SHORT
                ).show();
            }
    }
}