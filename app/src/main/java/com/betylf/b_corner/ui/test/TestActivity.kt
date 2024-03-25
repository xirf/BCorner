package com.betylf.b_corner.ui.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.ActivityTestBinding
import com.betylf.b_corner.ui.result.ResultActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private lateinit var nextBtn: Button
    private lateinit var prevBtn: Button
    private lateinit var progressBar: LinearProgressIndicator

    private lateinit var questionsHati: Array<String>
    private lateinit var questionsLisan: Array<String>
    private lateinit var questionsPerilaku: Array<String>

    private var index: Int = 0
    private var isButtonNext: Boolean = false
    private var minimumQuestoin: Int = 0

    private val databaseHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHandler.dropProblemTable()

        questionsHati = resources.getStringArray(R.array.questions_hati)
        questionsLisan = resources.getStringArray(R.array.questions_lisan)
        questionsPerilaku = resources.getStringArray(R.array.questions_perbuatan)

        progressBar = binding.progressBar
        nextBtn = binding.btnNext
        prevBtn = binding.btnBack

        progressBar.setProgressCompat(0, true)
        nextBtn.setOnClickListener { nextFragment() }
        prevBtn.setOnClickListener { prevFragment() }

        Log.d("TestActivity", "onCreate: called attempt to display fragment")
        displayFragment()
    }

    private fun nextFragment() {
        if (!isButtonNext) {
            val toast = Toast.makeText(
                applicationContext, "Pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            if (index < questionsHati.size - 1) {
                index++
                isButtonNext = true
                displayFragment()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun prevFragment() {
        if (index > minimumQuestoin) {
            index--
            isButtonNext = false
            displayFragment()
        } else {
            finish()
        }
    }

    private fun displayFragment() {
        Log.d("TestActivity", "displayFragment: called")
        isButtonNext = false
        val bundle = Bundle()
        bundle.putString(TestFragment.EXTRA_HATI, questionsHati[index])
        bundle.putString(TestFragment.EXTRA_LISAN, questionsLisan[index])
        bundle.putString(TestFragment.EXTRA_PERILAKU, questionsPerilaku[index])
        bundle.putInt(TestFragment.EXTRA_INDEX, index)

        progressBar.setProgressCompat((index + 1) * 100 / questionsHati.size, true)

        val fragment = TestFragment()
        fragment.arguments = bundle
        fragment.registerDataCallback { key, value ->
            isButtonNext = value
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }
}