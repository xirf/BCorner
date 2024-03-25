package com.betylf.b_corner.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.betylf.b_corner.R
import com.betylf.b_corner.databinding.ActivityWelcomeBinding
import com.betylf.b_corner.ui.login.LoginActivity
import com.betylf.b_corner.ui.register.RegisterActivity
import me.relex.circleindicator.CircleIndicator3
import java.util.Timer
import java.util.TimerTask

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        renderViewPager()
        startTimer()
        binding.btnMasuk.setOnClickListener(this)
        binding.btnDaftar.setOnClickListener(this)

        disableActionBar()

    }

    private fun disableActionBar() {
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            WindowInsetsCompat.CONSUMED
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_masuk -> startIntent(LoginActivity::class.java)
            R.id.btn_daftar -> startIntent(RegisterActivity::class.java)
        }
    }

    private fun startIntent(target: Class<*>) {
        val intent = Intent(this, target)
        startActivity(intent)
        finish()
    }

    private fun renderViewPager() {
        val images = listOf(
            R.drawable.welcome_image_1,
            R.drawable.welcome_image_2,
            R.drawable.welcome_image_3
        )
        val texts = resources.getStringArray(R.array.intro_array).toList()

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = SliderAdapter(images, texts)
        val indicator: CircleIndicator3 = findViewById(R.id.indicator)

        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)
    }

    private fun startTimer() {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            val viewPager: ViewPager2 = findViewById(R.id.viewPager)
            if (viewPager.currentItem == 2) {
                viewPager.currentItem = 0
            } else {
                viewPager.currentItem += 1
            }
        }
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 5000, 5000)
    }
}