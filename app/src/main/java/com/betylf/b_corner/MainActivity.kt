package com.betylf.b_corner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.ActivityMainBinding
import com.betylf.b_corner.ui.test.TestActivity
import com.betylf.b_corner.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val isLoggedIn = databaseHandler.isLoggedIn()

        if (!isLoggedIn) {
            goToWelcome()
        } else {
            setContentView(binding.root)
            binding.tvName.text =
                databaseHandler.getUsername(databaseHandler.getLoggedInUser().toString())
                    .split(" ")[0]
            binding.startTest.setOnClickListener {
                val intent = Intent(this, TestActivity::class.java)
                startActivity(intent)
            }
        }

        binding.ibAccount.setOnClickListener {
            databaseHandler.clearLoggedIn()
            goToWelcome()
        }
    }

    private fun goToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }


}