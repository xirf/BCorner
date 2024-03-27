package com.betylf.b_corner.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var databaseHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHandler = DatabaseHandler(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val result = databaseHandler.getAllProblem()
        val result1 = result.count { it == 0 }
        val result2 = result.count { it == 1 }
        val result3 = result.count { it == 2 }

        if (result1 > result2 && result1 > result3) {
            binding.result.text = resources.getString(R.string.desc_hati)
        } else if (result2 > result1 && result2 > result3) {
            binding.result.text = resources.getString(R.string.desc_lisan)
        } else {
            binding.result.text = resources.getString(R.string.desc_perilaku)
        }

        binding.result.text
        return binding.root
    }

}