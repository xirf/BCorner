package com.betylf.b_corner.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.betylf.b_corner.MainActivity
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disableActionBar()


        binding.btnMasuk.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val isUserAvailable = databaseHandler.getAccount(username)
                if (isUserAvailable == null) {
                    databaseHandler.addAccount(
                        username,
                        password,
                        username
                    )
                }

                databaseHandler.setLoggedIn(username = username, name = isUserAvailable.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.etEmail.error = getString(R.string.field_required)
                binding.etPassword.error = getString(R.string.field_required)
            }
        }
    }

    private fun disableActionBar() {
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            WindowInsetsCompat.CONSUMED
        }
    }
}