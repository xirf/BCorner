package com.betylf.b_corner.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.FragmentTreatmentBinding

class TreatmentFragment : Fragment() {
    private lateinit var binding: FragmentTreatmentBinding
    private lateinit var databaseHandler: DatabaseHandler
    private var names: Array<String> = arrayOf()
    private var descriptions: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHandler = DatabaseHandler(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTreatmentBinding.inflate(layoutInflater)

        val result = databaseHandler.getAllProblem()
        val result1 = result.count { it == 0 }
        val result2 = result.count { it == 1 }
        val result3 = result.count { it == 2 }

        if (result1 > result2 && result1 > result3) {
            names = resources.getStringArray(R.array.poin_hati)
            descriptions = resources.getStringArray(R.array.step_hati)
        } else if (result2 > result1 && result2 > result3) {
            names = resources.getStringArray(R.array.poin_lisan)
            descriptions = resources.getStringArray(R.array.step_lisan)
        } else {
            names = resources.getStringArray(R.array.poin_perbuatan)
            descriptions = resources.getStringArray(R.array.step_perbuatan)
        }

//        layout manager
        binding.lvTreatment.layoutManager = LinearLayoutManager(requireContext())
        val treatmentAdapter = TreatmentAdapter(names, descriptions)
        binding.lvTreatment.adapter = treatmentAdapter

        return binding.root
    }
}