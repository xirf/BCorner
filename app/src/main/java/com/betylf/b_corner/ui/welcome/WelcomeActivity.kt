package com.betylf.b_corner.ui.welcome

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.betylf.b_corner.R
import me.relex.circleindicator.CircleIndicator3
import java.util.Timer
import java.util.TimerTask

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val images = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background
        )
        val texts = listOf("Welcome to B Corner", "Swipe to see more", "Enjoy your time")
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = SliderAdapter(images, texts)
        viewPager.adapter = adapter
        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)

        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (viewPager.currentItem == images.size - 1) {
                viewPager.currentItem = 0
            } else {
                viewPager.currentItem += 1
            }
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)

    }
}