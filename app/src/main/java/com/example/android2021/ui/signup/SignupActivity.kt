package com.example.android2021.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android2021.R
import com.example.android2021.databinding.ActivitySignupBinding
import com.example.android2021.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity() : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var binding : ActivitySignupBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(layoutInflater);
        setContentView(binding.root);

        auth = Firebase.auth

        val username = binding.username
        val password = binding.password
        val passwordConfirm = binding.passwordConfirm   // Can remove this if you don't need it
        val signup = binding.signup;

        signup.setOnClickListener {
            auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(baseContext, LoginActivity::class.java).apply {
                            putExtra(R.string.extra_username.toString(), username.text.toString())
                            putExtra(R.string.extra_password.toString(), password.text.toString())
                        })
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Failed!!!",
                            Toast.LENGTH_SHORT
                        )
                    }
                }

        }
    }
}