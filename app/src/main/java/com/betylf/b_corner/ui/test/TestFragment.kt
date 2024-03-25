package com.betylf.b_corner.ui.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.betylf.b_corner.CommunicatorFragment
import com.betylf.b_corner.R
import com.betylf.b_corner.database.DatabaseHandler
import com.betylf.b_corner.databinding.FragmentTestBinding

class TestFragment : CommunicatorFragment() {
    private lateinit var databaseHandler: DatabaseHandler
    private lateinit var radioGroup: RadioGroup
    private lateinit var dataReceiver: (key: String, value: Boolean) -> Unit
    private lateinit var binding: FragmentTestBinding

    companion object {
        var EXTRA_HATI = "extra_hati"
        var EXTRA_LISAN = "extra_lisan"
        var EXTRA_PERILAKU = "extra_perilaku"
        var EXTRA_INDEX = "extra_index"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onReceive(key: String, value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun registerReceiver(dataReceiver: (key: String, value: Boolean) -> Unit) {
        this.dataReceiver = dataReceiver
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBinding.bind(view)
        databaseHandler = DatabaseHandler(requireContext())

        radioGroup = binding.radioGroup
        val r1: RadioButton = binding.cbOption1
        val r2: RadioButton = binding.cbOption2
        val r3: RadioButton = binding.cbOption3

        if (arguments != null) {
            r1.text = arguments?.getString(EXTRA_HATI).toString()
            r2.text = arguments?.getString(EXTRA_LISAN).toString()
            r3.text = arguments?.getString(EXTRA_PERILAKU).toString()
            val index: Int = arguments?.getInt(EXTRA_INDEX)?.toInt() ?: 0

            val selected = databaseHandler.getProblemAnswer(index)
            if (selected != -1) {
                when (selected) {
                    0 -> r1.isChecked = true
                    1 -> r2.isChecked = true
                    2 -> r3.isChecked = true
                }
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                dataReceiver("checked", true)
            } else {
                dataReceiver("checked", false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val index: Int = arguments?.getInt(EXTRA_INDEX)?.toInt() ?: 0
        val selected = when (radioGroup.checkedRadioButtonId) {
            R.id.cb_option1 -> 0
            R.id.cb_option2 -> 1
            R.id.cb_option3 -> 2
            else -> -1
        }

        if (selected != -1) {
            databaseHandler.setProblemAnswer(index, selected)
        }
    }

}