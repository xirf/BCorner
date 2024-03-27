package com.betylf.b_corner.ui.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.ActivityResultBinding
import com.google.android.material.tabs.TabLayoutMediator

class ResultActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityResultBinding
    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = databaseHandler.getAllProblem()
        val result1 = result.count { it == 0 }
        val result2 = result.count { it == 1 }
        val result3 = result.count { it == 2 }

        if (result1 > result2 && result1 > result3) {
            binding.tvTitle.text = "Hati"
        } else if (result2 > result1 && result2 > result3) {
            binding.tvTitle.text = "Lisan"
        } else {
            binding.tvTitle.text = "Perilaku"
        }

        val resultAdapter = ResultAdapter(this)
        binding.viewPager.adapter = resultAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Detail"
                1 -> tab.text = "Treatment"
            }
        }.attach()

        binding.fabBack.setOnClickListener {
            finish()
        }
    }

}