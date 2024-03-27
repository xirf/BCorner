package com.betylf.b_corner.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.ActivityRegisterBinding
import com.betylf.b_corner.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            login.setOnClickListener() {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }

            btnDaftar.setOnClickListener() {
                if (etEmail.text.toString().isEmpty() || etPassword.text.toString()
                        .isEmpty() || etUsername.text.toString().isEmpty()
                ) {
                    etUsername.error = "Username tidak boleh kosong"
                    etEmail.error = "Email tidak boleh kosong"
                    etPassword.error = "Password tidak boleh kosong"
                } else if (etPassword.text.toString().length < 6) {
                    etPassword.error = "Password minimal 6 karakter"
                } else if (etPassword.text.toString() != etRepeatPassword.text.toString()) {
                    etRepeatPassword.error = "Password tidak sama"
                } else {
                    databaseHandler.addAccount(
                        name = etUsername.text.toString(),
                        username = etEmail.text.toString(),
                        password = etPassword.text.toString()
                    )

                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}